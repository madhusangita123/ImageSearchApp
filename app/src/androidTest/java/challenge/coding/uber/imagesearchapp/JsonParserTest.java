package challenge.coding.uber.imagesearchapp;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.io.InputStream;

import challenge.coding.uber.imagesearchapp.model.SearchImageResponse;
import challenge.coding.uber.imagesearchapp.utils.AppUtils;
import challenge.coding.uber.imagesearchapp.webservice.JsonParser;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class JsonParserTest {

    @Test
    public void parseResponseTest(){
        Context context = InstrumentationRegistry.getTargetContext();
        InputStream is = context.getResources().openRawResource( R.raw.imagesearchresponse );
        String json = AppUtils.getJsonStringFromRawFile(is);
        SearchImageResponse parsedObject = JsonParser.parseResponse( json );
        assertThat( parsedObject,notNullValue(  ));
        assertNotNull( parsedObject.photoCollection );
        assertEquals(parsedObject.photoCollection.pageNumber,1 );
        assertEquals(parsedObject.photoCollection.numberOfPages,1432 );
        assertEquals(parsedObject.photoCollection.perPage,100 );
        assertEquals(parsedObject.photoCollection.photos.size(),98 );
    }
}
