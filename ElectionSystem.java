import java.util.*;
public class ElectionSystem
{
    private static Election election;

    public static void setUpRoster(LinkedList<String> candidates, int total)
    {
        election = new Election();
        election.initializeCandidates(candidates);
        election.maxVotes = total;
    }

    public static void getTopK(int k)
    {
        System.out.println("Top " + k + " Candidates: ");
        List<String> topK = election.getTopKCandidates(k);
        for (int i = 0; i < topK.size(); i++)
        {
            System.out.println(topK.get(i));
        }
    }

    public static void castVotes()
    {
        while (election.totalVotes < election.maxVotes)
        {
            election.castRandomVote();
        }
    }

    public static void rigElection(String name)
    {
        System.out.println("Votes rigged in favor of " + name);
        election.rigElection(name);
    }

    public static void Audit()
    {
        election.auditElection();
    }

    public static void SimulateElection(LinkedList<String> roster, int max)
    {
        setUpRoster(roster, max);
        System.out.println();
        castVotes();
        getTopK(3);
        System.out.println();
        Audit();
        System.out.println();
        Random rng = new Random();
        if (rng.nextInt(2) == 1)
        {
            String candidate = roster.get(rng.nextInt(roster.size()));
            rigElection(candidate);
            System.out.println();
            getTopK(3);
            System.out.println();
            Audit();
        }

    }

    public static void main(String[]args)
    {
        LinkedList<String> names = new LinkedList<>();

        names.push("Allison Green");
        names.push("Eli Croft");
        names.push("Morris Anton");
        names.push("Henry Hart");
        names.push("Ray Manchester");
        names.push("Nathan Drake");

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter number of people in the Electorate: ");
        int num = scan.nextInt();
        scan.close();
        SimulateElection(names, num);
    }
}
