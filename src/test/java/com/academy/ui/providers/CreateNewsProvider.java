package com.academy.ui.providers;

import org.testng.annotations.DataProvider;
import com.academy.ui.constants.NewsTags;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateNewsProvider {

    @DataProvider(name = "tagsListSelect")
    public Iterator<Object[]> dpTestTagsListSelect() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[] {
                new NewsTags[]{NewsTags.NEWS, NewsTags.EVENTS,NewsTags.EDUCATION},
                new NewsTags[]{NewsTags.INITIATIVES, NewsTags.ADS}
        });
        data.add(new Object[] {
                new NewsTags[]{NewsTags.NEWS, NewsTags.EVENTS,NewsTags.EDUCATION},
                new NewsTags[]{NewsTags.NEWS, NewsTags.EVENTS}
        });
        data.add(new Object[] {
                new NewsTags[]{NewsTags.INITIATIVES,NewsTags.ADS,NewsTags.EDUCATION},
                new NewsTags[]{NewsTags.NEWS, NewsTags.EVENTS},
        });

        return data.iterator();
    }
}
