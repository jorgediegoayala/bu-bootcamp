import java.io.*; 
import java.util.ArrayList;
 


public class GradeAnalyzer {
 
    public record ReadScoresResult(ArrayList<Integer> scores, int linesSkipped) {}
    public record GradeBands(int a, int b, int c, int d, int f){};
    public static void main(String[] args) {
        // Step 1: read scores from file
        ReadScoresResult readScores = readScores("c:\\work\\masters\\Module1\\scores.txt");
        
        // Step 2: calculate statistics

        double average = calculateAverage(readScores.scores);

        //finding highest and lowest
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;
        for (int i = 0; i < readScores.scores.size(); i++) {
            var currentScore = readScores.scores.get(i);
            if (currentScore > highest)
                highest = currentScore;
            if (currentScore < lowest)
                lowest = currentScore;
        }

        GradeBands gradeBands = calculateGradeBands(readScores.scores);
        // Step 3: write and print report
        String report = generateReport(readScores, gradeBands, average, highest, lowest);
        System.out.print(report);
        writeReport(report, "c:\\work\\masters\\Module1\\report.txt");
    } 
 
    private static void writeReport(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            System.err.println("File write failed: " + e.getMessage());
        }
    }

    // Returns a list of valid scores read from the file
    public static ReadScoresResult readScores(String filename) {
        
        ArrayList<Integer> scores = new ArrayList<Integer>();
        int linesSkipped = 0;
 
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    linesSkipped++;
                    continue;
                }
                try{
                    int parsedInt = Integer.parseInt(line);
                    scores.add(parsedInt);
                } catch(NumberFormatException e){
                    linesSkipped ++;
                    System.out.println(String.format("Invalid score '%s', %s",line, e.getMessage()));        
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
        return new ReadScoresResult(scores, linesSkipped);
    }
 
    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) return 0;
        double result = 0;
        for (int i = 0; i < scores.size(); i++) {
            result = result + scores.get(i);
        }
        return result / scores.size();
    } 
 
    public static GradeBands calculateGradeBands(ArrayList<Integer> scores){
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int f = 0;

        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) >= 90)
                a++;
            if (scores.get(i) >= 80 && scores.get(i) <= 89 )
                b++;
            if (scores.get(i) >= 70 && scores.get(i) <= 79 )
                c++;
            if (scores.get(i) >= 60 && scores.get(i) <= 69 )
                d++;
            if (scores.get(i) < 60 )
                f++;
        };
        return new GradeBands(a, b, c, d, f);
    }
    
    public static String generateReport(ReadScoresResult readScores,
                                    GradeBands gradeBands,
                                   double avg, int high, int low) {
        
        var builder = new StringBuilder();
        if (readScores.scores.isEmpty())
            builder.append("zero scores found to report.");
        else
            builder.append("=== Grade Analysis Report ===")
            .append(System.lineSeparator())
            
            .append(String.format("Total lines read from file: %d", readScores.scores.size() + readScores.linesSkipped))
            .append(System.lineSeparator())
            .append(String.format("Total scores processed: %d", readScores.scores.size()))
            .append(System.lineSeparator())
            .append(String.format("Invalid lines skipped: %d%n", readScores.linesSkipped)) 
            .append(System.lineSeparator())

            .append(String.format("Average score: %.2f", avg)) 
            .append(System.lineSeparator())
            .append(String.format("Highest score: %d", high)) 
            .append(System.lineSeparator())
            .append(String.format("Lowest score: %d%n", low)) 
            .append(System.lineSeparator())
            .append("Grade Distribution")
            .append(System.lineSeparator())
            .append(String.format("A (90-100): %d%n", gradeBands.a)) 
            .append(String.format("B (80-89): %d%n", gradeBands.b)) 
            .append(String.format("C (70-79): %d%n", gradeBands.c)) 
            .append(String.format("D (60-69): %d%n", gradeBands.d)) 
            .append(String.format("F (below 60): %d", gradeBands.f));
        return builder.toString();
    }
} 