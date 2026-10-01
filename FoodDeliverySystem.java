class FoodDeliverySystem {
    static class DeliveryTask extends Thread {
        private String taskDetails;

        public DeliveryTask(String name, int priority, String taskDetails) {
            super(name);
            setPriority(priority);
            this.taskDetails = taskDetails;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Thread Name: " + getName() + " | Assigned Priority: " + getPriority() + " | Activity: " + taskDetails + " (Step " + i + ")");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(getName() + " interrupted.");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeliveryTask orderThread = new DeliveryTask("OrderProcessing", 9, "Validating payment and dispatching order to kitchen");
        DeliveryTask trackingThread = new DeliveryTask("DeliveryTracking", 6, "Tracking live GPS coordinates of rider");
        DeliveryTask notifyThread = new DeliveryTask("Notification", 3, "Broadcasting push updates and estimated arrival time");

        orderThread.start();
        trackingThread.start();
        notifyThread.start();

        try {
            orderThread.join();
            trackingThread.join();
            notifyThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
    }
}
