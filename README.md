# ImageSearchApp
- This is a sample mobile app which uses the Flick Image Search API and shows the result in three column scrollable view. 
- It has a Search bar where you can enter name of image you want to search for. Such as kittens,dog,rose etc.
- The app supports endless scrolling, automatically requesting and displaying more images when the user scrolls to the bottom of the view.
- App uses Paging library from android architecture components to load image search result page by page.
- App follows Android developer guide to app architecture.
- Below is to show how Activity gets it's data from
	MainActivity-PhotosViewModel-PhotoCollectionSource-NetworkSource-Webservice
- ImageLoader class from challenge.coding.uber.imagesearchapp.imageloader package downloads the image from URL and caches the image in memory.	
-MemoryCache class uses LruCache for image chaching.
