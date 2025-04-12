import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ModifiedElectionSystem
{
    private static Election election;

    private static LinkedList<String> RandomizeCandidates()//Added a method to randomly generate a List of a random size
    {
        LinkedList<String> namePool = new LinkedList<>();

        String[] names = {"Cameron Tate", "Eli Mobsley", "Jill Scott", "Adam Walker", "Terry Cruz", "Sam Moore", "John Lindsey",
                "Paul Worthol", "Avery Matthews", "Hickory Dot", "James Adams",};

        for (int i = 0; i < names.length;i++)
        {
            namePool.push(names[i]);
        }

        Random rng = new Random();

        int CandidateCount = rng.nextInt(5) + 3;

        LinkedList<String> randomCandidates = new LinkedList<>();
        for (int i = 0; i < CandidateCount; i++)
        {
            int index = rng.nextInt(namePool.size());
            String candidate = namePool.remove(index);
            randomCandidates.add(candidate);
        }
        return randomCandidates;
    }
    public static void setUpRoster(LinkedList<String> candidates)
    {
        Random rng = new Random();
        election = new Election();
        election.initializeCandidates(candidates);
        election.maxVotes = rng.nextInt(25);//changed MaxVotes

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

    public static void SimulateElection() {
        LinkedList<String> roster = RandomizeCandidates();
        setUpRoster(roster);
        System.out.println();
        castVotes();
        getTopK(3);
        System.out.println();
        Audit();
        System.out.println();
        Random rng = new Random();
        if (rng.nextInt(2) == 1) {
            String candidate = roster.get(rng.nextInt(roster.size()));
            rigElection(candidate);
            System.out.println();
            getTopK(3);
            System.out.println();
            Audit();
        }
    }

    public static void main(String[] args)
    {
        SimulateElection();
    }
}
