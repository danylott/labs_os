// Run() is called from Scheduling.main() and is where
// the scheduling algorithm written by the user resides.
// User modification should occur within the Run() function.

package main;


import java.util.Comparator;
import java.util.Vector;
import java.io.*;

public class SchedulingAlgorithm {

  private static class ProcessPriorityComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
      sProcess first = (sProcess) o1;
      sProcess second = (sProcess) o2;

      if (first.priority == second.priority && first.cputime == second.cputime) {
        if (first.ioblocking == second.ioblocking) {
          return 0;
        }
        return first.ioblocking - second.ioblocking > 0 ? 1 : -1;
      }
      else if (first.priority == second.priority){
        return first.cputime - second.cputime > 0 ? 1 : -1;
      }
      return first.priority > second.priority ? 1 : -1;
    }
  }


  public static Results Run(int runtime, Vector processVector, Results result) {
    int i = 0;
    int comptime = 0;
    int currentProcess = 0;
    int previousProcess = 0;
    int size = processVector.size();
    int completed = 0;
    String resultsFile = "Summary-Processes";
    result.schedulingType = "Non-preemptive";
    result.schedulingName = "Shortest process next";
    initPriority(processVector);
    processVector.sort(new ProcessPriorityComparator());
    try {
      //BufferedWriter out = new BufferedWriter(new FileWriter(resultsFile));
      //OutputStream out = new FileOutputStream(resultsFile);
      PrintStream out = new PrintStream(new FileOutputStream(resultsFile));
      sProcess process = (sProcess) processVector.elementAt(currentProcess);
      out.println("Process: " + currentProcess + " registered... (" + process.toString() + ")");
      while (comptime < runtime) {
        if (process.cpudone == process.cputime) {
          completed++;
          out.println("Process: " + currentProcess + " completed... (" + process.toString() + ")");
          if (completed == size) {
            result.compuTime = comptime;
            out.close();
            return result;
          }
          for (i = size - 1; i >= 0; i--) {
            process = (sProcess) processVector.elementAt(i);
            if (process.cpudone < process.cputime) { 
              currentProcess = i;
            }
          }
          process = (sProcess) processVector.elementAt(currentProcess);
          out.println("Process: " + currentProcess + " registered... (" + process.toString() + ")");
        }      
        if (process.ioblocking == process.ionext) {
          out.println("Process: " + currentProcess + " I/O blocked... (" + process.toString() + ")");
          process.numblocked++;
          process.ionext = 0; 
          previousProcess = currentProcess;
          for (i = size - 1; i >= 0; i--) {
            process = (sProcess) processVector.elementAt(i);
            if (process.cpudone < process.cputime && previousProcess != i) { 
              currentProcess = i;
            }
          }
          process = (sProcess) processVector.elementAt(currentProcess);
          out.println("Process: " + currentProcess + " registered... (" + process.toString() + ")");
        }        
        process.cpudone++;       
        if (process.ioblocking > 0) {
          process.ionext++;
        }
        comptime++;
      }
      out.close();
    } catch (IOException e) { /* Handle exceptions */ }
    result.compuTime = comptime;
    return result;
  }

  private static void initPriority(Vector<sProcess> processes) {
    int index = 0;
    int prevCpuTime = 0;
    processes.sort(((o1, o2) -> {
      if (o1.cputime == o2.cputime) {
        return o1.ioblocking - o2.ioblocking;
      }
      return o1.cputime - o2.cputime;
    }));
    for (sProcess process : processes) {
      if(prevCpuTime == process.cputime) {
        index += 1;
      }
      prevCpuTime = process.cputime;
      process.priority = index;
    }
  }
}
