import ngrams.TimeSeries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994, 1995));

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(0.0, 100.0, 600.0, 500.0));

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    @Test
    public void testEmptyBasic() {
        TimeSeries catPopulation = new TimeSeries();
        TimeSeries dogPopulation = new TimeSeries();

        assertThat(catPopulation.years()).isEmpty();
        assertThat(catPopulation.data()).isEmpty();

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);

        assertThat(totalPopulation.years()).isEmpty();
        assertThat(totalPopulation.data()).isEmpty();
    }
    @Test
    public void testYears(){
        TimeSeries lld=new TimeSeries();
        lld.put(2000,0.01);
        lld.put(2001,0.02);
        lld.put(2002,0.03);
        lld.put(2003,0.04);
        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(2000, 2001, 2002, 2003));
        assertThat(lld.years()).isEqualTo(expectedYears);
    }
    @Test
    public void testYears01(){
        TimeSeries lld= new TimeSeries();
        lld.put(1000,0.01);
        lld.put(2000,0.9);
        lld.put(3000,100.9);
        lld.remove(2000);
        lld.put(4000,900.0);
        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1000,3000,4000));
        assertThat(lld.years()).isEqualTo(expectedYears);
    }
    @Test
     public void testData(){
        TimeSeries lld=new TimeSeries();
        lld.put(2000,0.01);
        lld.put(2001,0.02);
        lld.put(2002,0.03);
        lld.put(2003,0.04);
        List<Double> excepteddata = new ArrayList<>
                (Arrays.asList(0.01, 0.02, 0.03, 0.04));
        assertThat(lld.data()).isEqualTo(excepteddata);
    }
    @Test
    public void testDividedBy(){
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1991,0.05);
        dogPopulation.put(1992,250.0);
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);
        TimeSeries res=catPopulation.dividedBy(dogPopulation);
        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994));
        assertThat(res.years()).isEqualTo(expectedYears);
        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(0.0, 0.4, 0.5));
        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(res.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }
} 