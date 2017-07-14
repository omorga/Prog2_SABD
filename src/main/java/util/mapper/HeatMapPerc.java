package util.mapper;
import configuration.Configuration;
import model.HeatMap;

import java.math.BigDecimal;


//classe che ci permette di mappare una matrice di HeatMap.
public class HeatMapPerc {

    private  HeatMap[][] heatmap;

    public HeatMapPerc(){
    }

    public  void init(){
        if (heatmap == null){
            int x;
            long count=0;
            HeatMap[][] heat= new HeatMap[(int) Configuration.NUM_CELL_X][(int) Configuration.NUM_CELL_Y];
            this.heatmap=heat;
            for(x=0; x<(int) Configuration.NUM_CELL_X; x=x+1) {
                int y;
                for (y =0; y <(int) Configuration.NUM_CELL_Y; y = y + 1) {
                    this.heatmap[x][y]= new HeatMap();
                    this.heatmap[x][y].setCell_id(count);
                    this.heatmap[x][y].setPercentage(0);
                    count = count + 1;
                }
            }
        }
    }

    public void setHeatmap(HeatMap[][] heatmap) {
        this.heatmap = heatmap;
    }
    public HeatMap getHeatmap(int x, int y) {
        return  this.heatmap[x][y];
    }
    public HeatMap[][] getHeatmap() {
        return  this.heatmap;
    }

    //calcola la percentuale di ciascuna cella e implementa la stampa della cella.
    public String calcule(long n) {
        StringBuilder sb = new StringBuilder();
        int x;
        for(x=0; x<(int) Configuration.NUM_CELL_X; x=x+1) {
            int y;
            for (y =0; y <(int) Configuration.NUM_CELL_Y; y = y + 1) {
                double perc=this.heatmap[x][y].getPercentage()/n*100;
                BigDecimal bg2 = new BigDecimal(perc);
                bg2 = bg2.setScale(2, BigDecimal.ROUND_HALF_UP);
                this.heatmap[x][y].setPercentage(bg2.doubleValue());
                sb.append(this.heatmap[x][y]).append(" ");
            }
        }
        return sb.toString();
    }
}