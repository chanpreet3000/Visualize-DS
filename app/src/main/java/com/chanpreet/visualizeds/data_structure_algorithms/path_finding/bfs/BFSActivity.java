package com.chanpreet.visualizeds.data_structure_algorithms.path_finding.bfs;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.GridBuilder;
import com.chanpreet.visualizeds.classes.GridItemOnClickListener;
import com.chanpreet.visualizeds.classes.Pair;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCard2Binding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSActivity extends VisualizerActivity implements GridItemOnClickListener {
    private static final int SPEED = 100;
    private static final int GRID_SIZE = 10;
    private static final int GRID_UNIT = 75;
    private static final Pair start = new Pair(0, 0);
    private static final Pair end = new Pair(5, 6);
    int[][] mat = new int[GRID_SIZE][GRID_SIZE];
    //TOP, RIGHT, BOTTOM, LEFT
    private static final int[] d4x = {-1, 0, 1, 0};
    private static final int[] d4y = {0, 1, 0, -1};
    List<List<ImageView>> listOfImageViews;
    List<Pair> ans = new ArrayList<>();
    boolean canVisualize = true;
    private ItemVisualizeInputCard2Binding binding1;

    @Override
    public void generateInputUI() {
        binding1 = ItemVisualizeInputCard2Binding.inflate(getLayoutInflater());
        binding1.button.setText("Reset Grid");
        binding1.button.setOnClickListener(v -> initializeGrid());
        binding1.editText.setVisibility(View.GONE);
        binding1.textView.setVisibility(View.GONE);

        binding.inputLinearLayout.addView(binding1.getRoot());

        initializeGrid();
    }

    private void initializeGrid() {

        GridBuilder.GridObject obj = GridBuilder.build(this, GRID_SIZE, GRID_UNIT);

        List<StepCard> stepCardList = new ArrayList<>();

        StepCard stepCard = new StepCard();
        stepCard.setTitle("Initial Grid");
        stepCard.setDescription("WHITE\t\t\t: EMPTY CELL" + "\n" +
                "RED\t\t\t\t: START CELL" + "\n" +
                "GREEN\t\t\t: END CELL" + "\n" +
                "BLACK\t\t\t: BLOCK CELL");
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

        canVisualize = true;
    }

    private boolean isValid(int x, int y) {
        return !(x < 0 || x >= GRID_SIZE || y < 0 || y >= GRID_SIZE);
    }

    @Override
    public void visualizeButtonClicked() {
        binding1.button.setEnabled(false);
        canVisualize = false;
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
                    handler.postDelayed(this, SPEED);
                } else {
                    binding1.button.setEnabled(true);
                }
            }
        };
        handler.post(runnable);
    }


    @Override
    public void onClick(View view, Pair loc) {
        if (!canVisualize) return;

        view.setBackgroundResource(GridBuilder.BLOCK_COLOR);
        mat[loc.first][loc.second] = 1;
    }
}
