package com.atelier.sunny.manager.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class EventManager {

    private static List<BetterTimerTask> timerTaskList = new ArrayList<>();
    private static final Timer timerManager = new Timer(true);
    private static final Logger logger = LoggerFactory.getLogger(EventManager.class);
    private static final long milliseconds = 300000; // 5 minutes

    public static void addTimer(BetterTimerTask task){
        timerTaskList.add(task);
    }

    public static boolean isTimerInList(String id){
        for (BetterTimerTask task: timerTaskList) {
            if (id == task.getId()) return true;
        }
        return false;
    }

    public static boolean shutdownTask(String id){
        for (BetterTimerTask task: timerTaskList) {
            if (id == task.getId()){
                logger.info("Shutting down task with id " + task.getId());
                return task.cancel();
            }
        }
        return false;
    }

    public static void shutdownTimer(){timerManager.cancel();}

    public static void startTimer(String id){
        for (BetterTimerTask task: timerTaskList) {
            if (id == task.getId()){
                timerManager.scheduleAtFixedRate(task, 0, milliseconds);
                logger.info("Task " + task.getId() + " had scheduled");
                return;
            }
        }
    }

    public static void startAllTimer(){
        for (BetterTimerTask task: timerTaskList) {
            timerManager.scheduleAtFixedRate(task, 0, milliseconds);
            logger.info("Task " + task.getId() + " had scheduled");
        }
    }
}
