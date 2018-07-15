package Easy;

import java.util.ArrayList;
import java.util.List;
import java.math.*;

public class L56_Merge_Intervals {

    public static List<Interval> merge(List<Interval> intervals) {
        if(intervals.size() <= 1)
            return intervals;
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
        List<Interval> result = new ArrayList<Interval>();
        Interval it = intervals.get(0);
        
        result.add(new Interval(it.start, it.end));

        for(int i = 1; i < intervals.size();i++)
        {
            Interval it1 = result.get(result.size()-1);
            Interval it2 = intervals.get(i);
            int s1 = it1.start;
            int e1 = it1.end;
            int s2 = it2.start;
            int e2 = it2.end;
            if(s1 < s2 && e1 < s2)
            {
                result.add(new Interval(s2, e2));
            }
            else
            {
                Interval it3 = new Interval(Math.min(s1, s2), Math.max(e1, e2));
                result.remove(it1);
                result.add(it3);
            }
        }
        return result;
    }
    

    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}

class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
}
