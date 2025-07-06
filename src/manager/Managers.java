package manager;

 public  class Managers {
     static InMemoryTaskManager manager = new InMemoryTaskManager();

     static <T extends TaskManager> TaskManager getDefault(){
         return manager;
     }

     static HistoryManager getDefaultHistory() {
         return manager.history;
     }

}
