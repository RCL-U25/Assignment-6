import java.util.*;

public class Election
{
    public LinkedList<String> Roster;
    public HashMap<String, Integer> voteMap;
    public int totalVotes = 0;
    public int maxVotes;



    public void initializeCandidates(LinkedList<String> candidates)
    {
        voteMap = new HashMap<>();
        Roster = candidates;
        for (String r : Roster)
        {
            voteMap.put(r, 0);
        }


    }

    public void castVote(String candidate)
    {
        int votes = voteMap.get(candidate) + 1;
        voteMap.replace(candidate, votes);
        resortVotes(candidate);
        totalVotes++;
    }


    public void castRandomVote()
    {

        Random rng = new Random();
        int raffle = rng.nextInt(Roster.size());
        String picked = Roster.get(raffle);
        int votes = voteMap.get(picked) + 1;
        voteMap.replace(picked, votes);
        resortVotes(picked);
        totalVotes++;
    }

    public void rigElection(String Candidate)
    {
        int index = Roster.indexOf(Candidate);
        if (index == 0)
        {
            return;
        }
        while (index > 0)
        {
            String nextUp = Roster.get(index - 1);
            int candidateVotes = voteMap.get(Candidate);
            int nextUpVotes = voteMap.get(nextUp);

            if (candidateVotes < nextUpVotes)
            {
                Roster.set(index, nextUp);
                Roster.set(index - 1, Candidate);
                voteMap.replace(nextUp, candidateVotes);
                voteMap.replace(Candidate,nextUpVotes);
                index--;
            } else if (candidateVotes == nextUpVotes)
            {

                Roster.set(index,nextUp);
                Roster.set(index - 1, Candidate);
                voteMap.replace(Candidate, candidateVotes + 1);
                voteMap.replace(nextUp, nextUpVotes - 1);
                index--;
            }else{
                index--;
            }
            resortVotes(Candidate);
        }


    }


    private void resortVotes(String candidate)
    {
        int index = Roster.indexOf(candidate);
        while (index > 0) {
            String above = Roster.get(index - 1);
            if (voteMap.get(candidate) > voteMap.get(above)) {
                // Swap positions
                Roster.set(index, above);
                Roster.set(index - 1, candidate);
                index--;
            } else {
                break;
            }
        }

        // Push downward if needed (in case candidate was rigged and now over-promoted)
        while (index < Roster.size() - 1) {
            String below = Roster.get(index + 1);
            if (voteMap.get(candidate) < voteMap.get(below)) {
                // Swap positions
                Roster.set(index, below);
                Roster.set(index + 1, candidate);
                index++;
            } else {
                break;
            }
        }
    }


    public LinkedList<String> getTopKCandidates(int k)
    {
        LinkedList<String> topCandidates = new LinkedList<>();
        if (k > Roster.size())
        {
            k = Roster.size();
        }
        for (int i = 0; i < k; i++)
        {
            topCandidates.add(Roster.get(i));
        }

        return topCandidates;
    }

    public void auditElection()
    {
        for (int i = 0; i < Roster.size();i++)
        {
            System.out.println(Roster.get(i) + " - " + voteMap.get(Roster.get(i)));
        }
    }
}

