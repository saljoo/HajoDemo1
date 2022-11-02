package fi.utu.tech.assignment5;

import java.util.ArrayList;
import java.util.List;

// Käytetään tehtässä 1 muokattua GradingTask-oliota
import fi.utu.tech.common.GradingTask;
// Allokointifunktiot implementoidaan TaskAllocator-luokassa
import fi.utu.tech.common.TaskAllocator;

import fi.utu.tech.common.Submission;
import fi.utu.tech.common.SubmissionGenerator;
import fi.utu.tech.common.SubmissionGenerator.Strategy;


public class App5 {
    public static void main( String[] args )
    {
        // Kopioi edellisen tehtävän ratkaisu tähän lähtökohdaksi
        // Tässä tehtävässä vaaditaan myös muutoksia TaskAllocator-luokkaan
        // Otetaan funktion aloitusaika talteen suoritusajan laskemista varten
        long startTime = System.currentTimeMillis();

        // Generoidaan kasa esimerkkitehtäväpalautuksia
        List<Submission> ungradedSubmissions = SubmissionGenerator.generateSubmissions(21, 200, Strategy.STATIC);

        // Tulostetaan tiedot esimerkkipalautuksista ennen arviointia
        for (var ug : ungradedSubmissions) {
            System.out.println(ug);
        }

        // Luodaan uusi arviointitehtävä
        int taskCount = 21;
        List<GradingTask> gt = TaskAllocator.allocate(ungradedSubmissions, taskCount);
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < taskCount; i++){
            Thread thread = new Thread(gt.get(i));
            threads.add(thread);
            thread.start();
        }
        for(Thread thread : threads){
            try{
                thread.join();
            } catch(InterruptedException e){
                System.out.println("InterruptedException");
            }
        }
        List<Submission> gradedSubmissions = new ArrayList<>();
        for(GradingTask gt1 : gt){
            gradedSubmissions.addAll(gt1.getGradedSubmissions());
        }
        

        // Tulostetaan arvioidut palautukset
        System.out.println("------------ CUT HERE ------------");
        for (var gs : gradedSubmissions) {
            System.out.println(gs);
        }

        // Lasketaan funktion suoritusaika
        System.out.printf("Total time for grading: %d ms%n", System.currentTimeMillis()-startTime);
    }
}