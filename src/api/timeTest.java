package api;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.*;

import com.google.gson.*;
import org.json.JSONObject;

import com.google.gson.JsonElement;
import org.json.JSONArray;

/**
 * this class is for testing the time of some algorithms.
 * the algo's:
 *          - SHORTEST_PATH
 *          - CONNECTED_COMPONENTS
 *          - CONNECTED_COMPONENT
 */
public class timeTest {
    public static void main(String[] args) throws IOException {
        DWGraph_DS graph = new DWGraph_DS();
        DWGraph_Algo ga = new DWGraph_Algo();
        ga.init(graph);

        HashMap<String, Long> scoresInShortPath = new HashMap<>();
        HashMap<String, Long> scoresInSCC = new HashMap<>();
        HashMap<DWGraph_DS, String> graphs = new HashMap<>();


        String path = "C:\\Users\\Amit Hajaj\\IdeaProjects\\OOP-course-game\\data";
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.getName().endsWith(".json")) {
                String temp = new String(Files.readAllBytes(Paths.get(file.toString())));
                graphs.put( graph.fromJson(temp), file.getName());
            }
        }

//        String JsonGraph = new String(Files.readAllBytes(Paths.get(path + "\\G_10_80_0.json")));
//        ga.init(graph.fromJson(JsonGraph));

        for (DWGraph_DS g : graphs.keySet()) {
            ga.init(g);
            //time shortest_path on each node to each node.
            long startTime = System.currentTimeMillis();
            ga.shortestPath(0, g.nodeSize()/2);


            long endTime = System.currentTimeMillis();

            scoresInShortPath.put(graphs.get(g), endTime-startTime);

            //time SCC algo.
            startTime = System.currentTimeMillis();
            ga.printSCCs();
            endTime = System.currentTimeMillis();
            scoresInSCC.put(graphs.get(g), endTime-startTime);
        }

        System.out.println("---------shortPath times---------"+scoresInShortPath.toString()+"\n -------SCC times--------\n"+scoresInSCC.toString());
        System.out.println("");
    }
}
