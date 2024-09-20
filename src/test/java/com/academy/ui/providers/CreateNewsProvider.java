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

    @DataProvider(name = "validData")
    public Iterator<Object[]> dpTestValidData() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[] {
                "Title 1",
                new NewsTags[]{NewsTags.NEWS},
                "TestTestTestTestTestTestTestTestTestTestTest"
        });
        data.add(new Object[] {
                "Title 2",
                new NewsTags[]{NewsTags.EVENTS, NewsTags.EDUCATION},
                "DescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescription"
        });
        data.add(new Object[] {
                "Title 3",
                new NewsTags[]{NewsTags.INITIATIVES, NewsTags.ADS, NewsTags.NEWS},
                "NewNewNewNewNewNewNewNewNewNewNewNew"
        });

        return data.iterator();
    }

    @DataProvider(name = "validDataSourceLink")
    public Iterator<Object[]> dpTestSourceLink() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[] {
                "Test Title 11",
                new NewsTags[]{NewsTags.INITIATIVES, NewsTags.ADS},
                "Test Content 11,Test Content 11,Test Content 11,Test Content 11,Test Content 11",
                "https://www.greencity.cx.ua/#/events/3001"
        });
        data.add(new Object[] {
                "Test Title 22",
                new NewsTags[]{NewsTags.INITIATIVES, NewsTags.ADS},
                "Test Content 22,Test Content 2,Test Content 2,Test Content 2,Test Content 2",
                "https://www.greencity.cx.ua/#/events/3092"
        });
        data.add(new Object[] {
                "Test Title 33",
                new NewsTags[]{ NewsTags.ADS},
                "Test Content 33,Test Content 3,Test Content 3,Test Content 3,Test Content 3",
                "https://www.greencity.social/#/greenCity"
        });

        return data.iterator();
    }

}
