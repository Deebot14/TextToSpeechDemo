# TextToSpeechDemo
This app was a personal project to explore the Text-to-Speech (TTS) API in Android and understand how to convert user-entered text into spoken audio.

## Programming Language
Java

## App Overview
The app allows users to enter text into an input field and tap a button to hear the text spoken out loud using Android’s TextToSpeech engine.

## Features
- ### Text-to-Speech Functionality
 Uses Android’s TextToSpeech API to convert user input into audible speech.

- ### Language Setup
 Automatically sets the speech language to US English using Locale.US. It checks for language support and logs errors when the specified language is missing or unsupported.

- ### Input Validation
 Displays a toast message if the input field is empty, prompting users to enter text before tapping the “Speak” button.

- ### Speech Queue Management
 Uses TextToSpeech.QUEUE_FLUSH to immediately speak the most recent input by flushing any queued utterances.

- ### Resource Cleanup
 Ensures proper resource management by stopping and shutting down the TextToSpeech engine in onDestroy() to avoid memory leaks.
