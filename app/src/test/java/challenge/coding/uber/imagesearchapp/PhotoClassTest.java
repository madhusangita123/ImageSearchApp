package challenge.coding.uber.imagesearchapp;

import org.junit.Test;

import challenge.coding.uber.imagesearchapp.model.Photo;

import static org.junit.Assert.assertEquals;

public class PhotoClassTest {

    @Test
    public void isCorrectURL(){
        Photo photo = new Photo();
        photo.id = "44855780794";
        photo.secret = "b7b4290780";
        photo.server = "1955";
        photo.farm = "2";
        String actualURL = photo.getUrlToImage();
        String expectedURL = "http://farm2.static.flickr.com/1955/44855780794_b7b4290780.jpg";
        assertEquals(actualURL,expectedURL);
    }

    @Test
    public void samePhotoequalsTest(){
        Photo photo = new Photo();
        photo.id = "44855780794";
        photo.secret = "b7b4290780";
        photo.server = "1955";
        photo.farm = "2";

        boolean actual = photo.equals( photo );
        boolean expected = true;

        assertEquals(actual,expected);
    }

    @Test
    public void diffTwoPhotosequalsTest(){
        Photo photo1 = new Photo();
        photo1.id = "44855780794";
        photo1.secret = "b7b4290780";
        photo1.server = "1955";
        photo1.farm = "2";

        Photo photo2 = new Photo();
        photo2.id = "44855780794";
        photo2.secret = "b7b4290780";
        photo2.server = "19556";
        photo2.farm = "2";

        boolean actual = photo1.equals( photo2 );
        boolean expected = false;

        assertEquals(actual,expected);
    }

    @Test
    public void sameTwoPhotosequalsTest(){
        Photo photo1 = new Photo();
        photo1.id = "44855780794";
        photo1.secret = "b7b4290780";
        photo1.server = "1955";
        photo1.farm = "2";

        Photo photo2 = new Photo();
        photo2.id = "44855780794";
        photo2.secret = "b7b4290780";
        photo2.server = "1955";
        photo2.farm = "2";

        boolean actual = photo1.equals( photo2 );
        boolean expected = true;

        assertEquals(actual,expected);
    }
}
