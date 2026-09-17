import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Candidate {
    int Candidate_id;
    String name;
    int aptitude;
    int technical;
    int communication;

    public Candidate(int Candidate_id, String name, int aptitude, int technical, int communication) {
        this.Candidate_id = Candidate_id;
        this.name = name;
        this.aptitude = aptitude;
        this.technical = technical;
        this.communication = communication;
    }

    public int getTotalScore() {
        return aptitude + technical + communication;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("[,\\s]+");

        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }

        int n = sc.nextInt();
        int k = sc.nextInt();

        Candidate[] candidates = new Candidate[n];

        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            String name = sc.next();
            int aptitude = sc.nextInt();
            int technical = sc.nextInt();
            int communication = sc.nextInt();
            candidates[i] = new Candidate(id, name, aptitude, technical, communication);
        }

        Arrays.sort(candidates, new Comparator<Candidate>() {
            @Override
            public int compare(Candidate c1, Candidate c2) {
                int scoreDiff = Integer.compare(c2.getTotalScore(), c1.getTotalScore());
                if (scoreDiff != 0) {
                    return scoreDiff;
                }
                return Integer.compare(c1.Candidate_id, c2.Candidate_id);
            }
        });

        int limit = Math.min(k, n);
        for (int i = 0; i < limit; i++) {
            System.out.println(candidates[i].Candidate_id + " " + candidates[i].name + " " + candidates[i].getTotalScore());
        }

        sc.close();
    }
}
