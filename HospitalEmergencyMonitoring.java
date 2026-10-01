class HospitalEmergencyMonitoring {
    static class MonitoringTask extends Thread {
        private String taskDescription;

        public MonitoringTask(String name, int priority, String taskDescription) {
            super(name);
            setPriority(priority);
            this.taskDescription = taskDescription;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Thread Name: " + getName() + " | Priority: " + getPriority() + " | Activity: " + taskDescription + " [Cycle " + i + "]");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(getName() + " interrupted.");
                }
            }
        }
    }

    public static void main(String[] args) {
        MonitoringTask emergency = new MonitoringTask("EmergencyAlert", Thread.MAX_PRIORITY, "Critical ICU patient arrhythmia detection");
        MonitoringTask vitals = new MonitoringTask("VitalMonitor", Thread.NORM_PRIORITY, "Periodic pulse and oxygen saturation check");
        MonitoringTask report = new MonitoringTask("ReportGenerator", Thread.MIN_PRIORITY, "Preparing daily routine health metrics summary");

        emergency.start();
        vitals.start();
        report.start();

        try {
            emergency.join();
            vitals.join();
            report.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
    }
}
