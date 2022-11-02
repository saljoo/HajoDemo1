package fi.utu.tech.common;

import java.util.List;
import java.util.ArrayList;

/**
 * You need to modify this file
 */


public class TaskAllocator {

    /**
     * Allocator that creates list of two (2) GradingTask objects with each having half of the given submissions
     * @param submissions The submissions to be allocated
     * @return The two GradingTask objects in a list, each having half of the submissions
     */
    public static List<GradingTask> sloppyAllocator(List<Submission> submissions) {
        // TODO: Tehtävä 4
        List<GradingTask> arvioijat = new ArrayList<>();
        GradingTask gt1 = new GradingTask();
        GradingTask gt2 = new GradingTask();
        gt1.setUngradedSubmissions(submissions.subList(0, submissions.size()/2));
        gt2.setUngradedSubmissions(submissions.subList(submissions.size()/2, submissions.size()));
        arvioijat.add(gt1);
        arvioijat.add(gt2);
        return arvioijat;
    }


    
    /**
     * Allocate List of ungraded submissions to tasks
     * @param submissions List of submissions to be graded
     * @param taskCount Amount of tasks to be generated out of the given submissions
     * @return List of GradingTasks allocated with some amount of submissions (depends on the implementation)
     */
    public static List<GradingTask> allocate(List<Submission> submissions, int taskCount) {
        // TODO: Tehtävä 5
        List<GradingTask> arvioijat = new ArrayList<>();
        for(int i = 0; i < taskCount; i++){
            GradingTask gt = new GradingTask();
            arvioijat.add(gt);
        }
        int j = 0;
        for(int k = 0; k <= arvioijat.size(); k++){
            if(j == submissions.size()) break;
            if(k == arvioijat.size()) k = 0;
            List<Submission> apuLista = new ArrayList<>();
            apuLista = arvioijat.get(k).getUngradedSubmissions();
            apuLista.add(submissions.get(j));
            arvioijat.get(k).setUngradedSubmissions(apuLista);
            j++;
        }
        return arvioijat;
    }
}
