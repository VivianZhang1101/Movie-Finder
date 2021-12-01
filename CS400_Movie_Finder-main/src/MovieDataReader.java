// --== CS400 File Header Information ==--
// Name: Xianfu Luo
// Email: xluo96@wisc.edu
// Team: Blue
// Group: CG
// TA: Xi Chen
// Lecturer: Gary Dahl
// Notes to Grader:

import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;

/**
 * This class defines MovieDataReader to read data from file.
 * @author Xianfu Luo
 */
public class MovieDataReader implements MovieDataReaderInterface{

    private List<MovieInterface> data;
    // constructor
    public MovieDataReader(){
        data = new ArrayList<MovieInterface>();
    }

    /**
     * @param datas collected from CSV directly
     * @return 13 strings to match each column
     */
    private String[] getRowValue (String[] datas){
        String[] result = new String[13];
        Arrays.fill(result,"");
        int index = 0;
        boolean flag = false;
        for(var partOfData: datas){

            if(partOfData.startsWith("\"")){
                result[index] += partOfData.substring(1)+",";
                flag = true;
            } else if (partOfData.endsWith("\"")){
                result[index] += partOfData.substring(0,partOfData.length()-1);
                index++;
                flag = false;
            } else {
                result[index] += partOfData;
                if (flag) {
                    result[index] += ",";
                } else {
                    index++;
                }
            }
        }
        return result;
    }

    /**
     *
     * @param data Strings from 13 columns
     * @return a MovieObject
     */
    private MovieObject dataFormatHelper(String[] data) {
        String title = data[0];
        int year = Integer.parseInt(data[2]);
        String genres = data[3].replace("\"", "");
        String director = data[7];
        String description = data[11];
        Float avgVote = Float.parseFloat(data[12]);
        MovieObject result = new MovieObject(title,year,genres, director, description, avgVote);
        return result;
    }

    /**
     *
     * @param inputFileReader file path
     * @return a list of MovieObjects
     * @throws FileNotFoundException
     * @throws IOException
     * @throws DataFormatException
     */
    @Override public List<MovieInterface> readDataSet(Reader inputFileReader)
        throws FileNotFoundException, IOException, DataFormatException {
        if(inputFileReader == null){
            throw new FileNotFoundException("There is no such file");
        }
        inputFileReader.read();
        Scanner scanner = new Scanner(inputFileReader);
        scanner.nextLine(); // start from the second line
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] str = getRowValue(line.split(","));
            if(str.length != 13){
                throw new DataFormatException("Wrong data Format");
            }
            data.add(dataFormatHelper(str));
        }
        Collections.sort(data);
        return data;
    }

}
