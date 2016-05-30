# InstaGallery
API and application for image loading 

An API that returns a list of images

#Usage:
Create a new instance of IGImageLoader , Pass an existing list of Strings or Call loadAllDeviceImages to return uris for all the Images on the phone
example : mImageList = mImageLoader.loadImagesList(mImageUriList, 0, -1); Where 0 is the starting index of the list and -1 means ALL , you can replace -1 with the number of images
you want to return

In progress: Load image from  URL

To Sort or Filter your list : Call IGISort or IGIFilter , it will return a sorted list or a filtered list according to function

Note: The API still needs tweaking regarding caching the images and running on a different thread than the Main thread ,
I realized this problem mid-work and was too late to fix within the time frame .


