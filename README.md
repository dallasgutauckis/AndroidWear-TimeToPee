# TimeToPee

Specially made for use with Android Wear

## Setup

 0. [Set up your environment](http://developer.android.com/wear/preview/start.html) (requires the Developer Preview)
 0. Open the project
 0. Open `Trakt` class, fill in API key from your trakt.tv account: http://trakt.tv/api-docs
 0. Start the Wear emulator
 0. Connect your Android device
 0. Run the project

## Testing

 0. Search for a movie
 0. Tap the movie you want to watch
 0. A notification should appear (on the device and on Wear)
 0. Open the notification on Wear, swipe to the Pee action
 0. Tap the pee action
 0. The notification should disappear
 0. Wait 3 seconds
 0. The TIME TO PEE notification should appear
 0. *Profit*


## Awesome libraries used in this project

 - [RxJava](https://github.com/Netflix/RxJava)
 - [Picasso](http://square.github.io/picasso/)
 - [Retrofit](http://square.github.io/retrofit/)
 - [OkHttp](http://square.github.io/okhttp/) - I don't think this was really necessary, but I used it out of habit

## License

**Apache 2.0**

```
Copyright 2014 Dallas Gutauckis

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```