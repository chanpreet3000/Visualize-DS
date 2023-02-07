package com.chanpreet.visualizeds.data_structure_algorithms.path_finding.basics.bfs;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.GridBuilder;
import com.chanpreet.visualizeds.classes.GridItemOnClickListener;
import com.chanpreet.visualizeds.classes.Pair;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.databinding.ItemErrorAlertCardBinding;
import com.chanpreet.visualizeds.databinding.ItemSuccessAlertCardBinding;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCard2Binding;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCard3Binding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class PFBFSActivity extends VisualizerActivity implements GridItemOnClickListener {
    private static final int MIN_SPEED = 30;
    private static final int MAX_SPEED = 500;
    private static final int GRID_SIZE = 10;
    private static final int GRID_UNIT = 75;
    private static final Pair start = new Pair(0, 0);
    private static final Pair end = new Pair(5, 6);
    int[][] mat = new int[GRID_SIZE][GRID_SIZE];
    //TOP, RIGHT, BOTTOM, LEFT
    private static final int[] d4x = {-1, 0, 1, 0};
    private static final int[] d4y = {0, 1, 0, -1};
    List<List<ImageView>> listOfImageViews;
    boolean canVisualize = true;
    boolean canAddObstacle = true;

    private ItemVisualizeInputCard2Binding binding1;
    private ItemVisualizeInputCard3Binding binding2;


    @Override
    public void onCreate() {
        initializeGrid();
    }

    @Override
    public void generateInputUI() {

        binding.buttonHolder.setVisibility(View.GONE);
        binding1 = ItemVisualizeInputCard2Binding.inflate(getLayoutInflater());
        binding1.textView.setText("Reset Grid");
        binding1.button.setText("Reset");
        binding1.button.setOnClickListener(v -> initializeGrid());
        binding1.editText.setVisibility(View.GONE);

        binding2 = ItemVisualizeInputCard3Binding.inflate(getLayoutInflater());
        binding2.textView.setText("Set Tick Speed of PathFinder");
        binding2.slider.setValue(MIN_SPEED);
        binding2.slider.setValueFrom(MIN_SPEED);
        binding2.slider.setValueTo(MAX_SPEED);

        ItemErrorAlertCardBinding binding3 = ItemErrorAlertCardBinding.inflate(getLayoutInflater());
        binding3.textView.setText("The Grid should be RESET after each visualization.");

        ItemSuccessAlertCardBinding binding4 = ItemSuccessAlertCardBinding.inflate(getLayoutInflater());
        binding4.textView.setText("You can tap on the grid blocks to add obstacles.");

        binding.inputLinearLayout.addView(binding3.getRoot());
        binding.inputLinearLayout.addView(binding1.getRoot());
        binding.inputLinearLayout.addView(binding2.getRoot());
        binding.inputLinearLayout.addView(binding4.getRoot());
    }

    @Override
    public Map<String, Object> getVisualizationInformation() {
        return new HashMap<>();
    }

    private void initializeGrid() {
        canAddObstacle = true;

        GridBuilder.GridObject obj = GridBuilder.build(this, GRID_SIZE, GRID_UNIT);

        List<StepCard> stepCardList = new ArrayList<>();

        StepCard stepCard = new StepCard();
        stepCard.setTitle("Initial Grid");
        stepCard.setDescription(
                "EMPTY CELL \t: ⬜" +
                        "\n" +
                        "START CELL \t: \uD83D\uDFE9" +
                        "\n" +
                        "END CELL   \t\t\t: \uD83D\uDFE5" +
                        "\n" +
                        "BLOCK CELL \t: ⬛");
        stepCard.setData(obj.getView());
        stepCardList.add(stepCard);

        adapter.setStepCardList(stepCardList);

        //
        listOfImageViews = obj.getListOfImageViews();

        listOfImageViews.get(start.first).get(start.second).setBackgroundResource(GridBuilder.START_COLOR);
        listOfImageViews.get(end.first).get(end.second).setBackgroundResource(GridBuilder.END_COLOR);

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Pair p = new Pair(i, j);
                if (!p.equals(start) && !p.equals(end)) {
                    listOfImageViews.get(i).get(j).setOnClickListener(v -> onClick(v, p));
                }
            }
        }

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                mat[i][j] = 0;
            }
        }
        binding.visualizeButton.setEnabled(true);
        setView(true);
    }

    private boolean isValid(int x, int y) {
        return !(x < 0 || x >= GRID_SIZE || y < 0 || y >= GRID_SIZE);
    }

    @Override
    public void visualize() {
        binding.visualizeButton.setEnabled(false);
        setView(false);
        canAddObstacle = false;
        Handler handler = new Handler();

        int[][] vis = new int[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                vis[i][j] = 0;
            }
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.add(start);
        vis[start.first][start.second] = 1;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int sz = queue.size();
                boolean flag = false;
                for (int i = 0; i < sz; i++) {
                    Pair front = queue.poll();
                    assert front != null;

                    for (int j = 0; j < 4; j++) {
                        int x = front.first + d4x[j], y = front.second + d4y[j];
                        if (isValid(x, y) && vis[x][y] == 0 && mat[x][y] == 0) {
                            if (end.equals(new Pair(x, y))) {
                                queue.clear();
                                flag = true;
                                break;
                            } else {
                                vis[x][y] = 1;
                                listOfImageViews.get(x).get(y).setBackgroundResource(GridBuilder.PATH_COLOR);
                                queue.add(new Pair(x, y));
                            }
                        }
                    }
                    if (flag) break;
                }
                if (!queue.isEmpty()) {
                    handler.postDelayed(this, (long) binding2.slider.getValue());
                } else {
                    setView(true);
                }
            }
        };
        handler.post(runnable);
    }


    @Override
    public void onClick(View view, Pair loc) {
        if (!canAddObstacle) return;

        view.setBackgroundResource(GridBuilder.BLOCK_COLOR);
        mat[loc.first][loc.second] = 1;
    }

    private void setView(Boolean flag) {
        canVisualize = flag;
        binding2.slider.setEnabled(flag);
        binding1.button.setEnabled(flag);
    }
}
