

********************************************************************************
*   Competitors: Katharine Nelson and Suraj Masand			       *
*   School: Alpharetta High School in Alpharetta, GA			       *
*   State: Georgia							       *
*   Event: Mobile Application Development				       *
*   2015 FBLA National Leadership Conference				       *
*   									       *
*   README.txt for Kalendo						       *
********************************************************************************



________________________________________________________________________________
INTRODUCTION / ABOUT KALENDO
________________________________________________________________________________


The accompanying mobile application, Kalendo, is an Android application created by Katharine Nelson and Suraj Masand from Alpharetta High School for FBLA's Mobile Application Development competition at the 2015 National Leadership Conference. This app is designed for Android smartphones but is runnable on most android tablets.

Kalendo is available from the Google Play Store. Simply follow the link and download the app.

	https://play.google.com/store/apps/details?id=com.alpharettafbla.kalendo


With its simple design, Kalendo allows for students at Alpharetta High School to view the school's upcoming events. Designed with navigability in mind, it connects to the school's Google calendar and displays the events in three main views: a today view which shows the schedule for the current day, a list view which shows events for the next three months, and a calendar view which allows users to select a specific date and see the events. Users can also choose from a list of Twitter accounts from the school to stay up-to-date with Alpharetta High School's social media. Current Twitter options include @AHSRaiderFBLA - the school's largest student organization, @FultonCoSchools - the twitter for the school district (as Alpharetta High School does not have its own Twitter account), and @RaiderSport - the official account for the Alpharetta athletics department.

Kalendo was developed using the Java programming language and Eclipse Juno IDE. It was built from scratch, without the use of any online templates.



________________________________________________________________________________
INSTRUCTIONS FOR USING ECLIPSE
________________________________________________________________________________


Because this mobile application was created using Eclipse Juno, we recommend testing it in the same IDE (if using an IDE to view the code). The code may also be opened directly from the files or seen in the pdf called KalendoCode.

If you already have everything installed, you may skip to Step 4.


1. Ensure that your machine has at least JDK 6 installed. Download links can be found on the Oracle website.

	http://www.oracle.com/technetwork/java/javase/downloads/index.html


2. Go to the link, choose the download respective to your machine, and install Eclipse Classic 4.2.2. Once downloaded, decompress the zip file.

	https://eclipse.org/downloads/packages/release/juno/sr2.


3. Open Eclipse. You will have to install the Eclipse Plugin in order to access the Android Development Tools. Go to the link and follow the instructions to download and configure the ADT Plugin.

	https://developer.android.com/sdk/installing/installing-adt.html


4. Open the Eclipse application. At the top of the screen, find the option for Window --> Android SDK Manager. Select the following to download, if you have not downloaded them already:

	- Android SDK Tools
	- Android SDK Platform-tools
	- Android SDK Build-tools (rev. 21)
	- Android 5.1.1 (API 22)
	- Android 5.0.1 (API 21)
	- Android 4.4.2 (API 20)
	- Android Support Library
	- Google Play Services
	- Google Repository

Click “Install Packages” at the bottom and accept all licenses. If you encounter issues, make sure you are running Eclipse as an administrator. Once all  of the downloads are complete, restart Eclipse. 


5. First, extract this folder (KalendoFBLA2015) from the disc and save it. Then, in Eclipse, at the top, select File --> Import. Choose “Existing Android Code Into Workspace” under the Android folder. Click Next, then Browse. Navigate to and select the folder you just extracted and saved. It should contain both Android projects. Click Open or OK. Make sure that in the “Projects to import” section, both of the following are checked:

	- android-support-v7-appcompat (or appcompat_v7)
	- Kalendo

Select “Copy projects into workspace” and click Finish.


NOTE: If errors show up in Kalendo, click on Project --> Properties --> Android and ensure that the library reference to the appcompat has a green check-mark icon next to it. If it does not, remove the reference, click Add and select the library project that matches the appcompat version that was just imported. Click OK. Apply the new settings and click OK again. If errors are still showing, go to Project --> Clean --> Clean all projects. If the files are completely blank, delete the projects and try importing them again. If necessary, try re-extracting the folder from the zip file and re-importing the projects.



________________________________________________________________________________
RUNNING THE APP WITH AN ANDROID DEVICE
________________________________________________________________________________


This is the recommended way of testing Kalendo, smartphones being the ideal device. There are three possible ways that an Android device can be used.


Google Play Store (preferred):
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Kalendo is available from the Google Play Store. Simply follow the link and download the app.

	https://play.google.com/store/apps/details?id=com.alpharettafbla.kalendo


Downloading the APK Independently:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Alternatively, you may download the APK from your mobile device's browser or from this disc (with the file named Kalendo.apk). Make sure you have enabled apps from unknown sources within your device settings. You may need to look online to find out how to enable this on your specific device model.

From mobile browser: Follow the link and click download. Open the file and select Install.

	http://tinyurl.com/kalendo

With attached APK: Copy the file to your device, either through USB connection or by attaching it through email. Open the file and select Install.


Android Device with Eclipse:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

On your android smartphone, make sure you have USB Debugging enabled. You may need to look online to find out how to enable this on your specific device model.

Once enabled, connect your smartphone to your computer via USB. In Eclipse, at the top, select Run --> Run. If prompted on how to run the application, select Android Application. If prompted to select a compatible device, select your device's name under the Android Devices category and click OK.

NOTE: If your device does not show up in the list, try disconnecting and reconnecting it to you computer.

Eclipse will install the application on the smartphone and Kalendo should open automatically. The same steps can be performed for testing the app on an Android tablet, but the app runs best on a smartphone.



________________________________________________________________________________
RUNNING THE APP WITH AN EMULATOR
________________________________________________________________________________


While testing with an Android device is preferred, the app can also be tested with an emulator. In Eclipse, go to the top and select Window --> Android Virtual Device Manager. In the new window, make sure you have a compatible emulator (of at least API 17).

If one is not there or there is not one compatible, select Create and fill out the form. We recommend the following settings, if they are available:

	AVD Name: Tester
	Device: Galaxy Nexus
	Target: Android 5.0.1 - API Level 21
	CPU/ABI: ARM (armeabi-v7a)
	Skin: No skin
	RAM: 760
	VM Heap: 64
	Internal Storage: 300

Select OK and the new emulator should appear in the window. Exit the AVD Manager.

Open the MainActivity.java file (Kalendo --> src --> com.alpharettafbla.kalendo). Once that file is opened in Eclipse, go to the top and select Run --> Run. If prompted on how to run the application, select Android Application.

If no Android device is connected via USB, the program should automatically open through the emulator. Otherwise, if you are prompted to select an emulator, select the one that you recently created that matches the required minimum API level (minimum of 17). The emulator should now start up and automatically open the application for testing.

NOTE: Emulators generally run very slowly and may take more time to react to button clicks and other stimuli of the user. This is why testing on an Android smartphone is recommended. Additionally, formatting may be negatively affected by certain emulator settings.



________________________________________________________________________________
IMPORTANT NOTES
________________________________________________________________________________


● Since the app is meant for students to view school events, the events are read-only within the app (the user cannot create, delete, or edit them). The events can only be updated by those who have editable access to the Google calendar. This facilitates easier use of the application, for no log-ins are required and only the school may alter events.

● Internet connection is required to load events from the Google calendar.

● Android devices must meet the minimum API level of 17 to use Kalendo.

● Kalendo is designed for smartphones. All functionalities should still be available on other devices, such as tablets, but the formatting may be negatively affected.



________________________________________________________________________________