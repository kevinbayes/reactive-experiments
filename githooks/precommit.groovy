#!/usr/bin/env groovy

println("""

 / ___| |__   ___  ___| | _(_)_ __   __ _  __      _| |__   ___  ___
| |   | '_ \\ / _ \\/ __| |/ / | '_ \\ / _` | \\ \\ /\\ / / '_ \\ / _ \\/ __|
| |___| | | |  __/ (__|   <| | | | | (_| |  \\ V  V /| | | | (_) \\__ \\
 \\____|_| |_|\\___|\\___|_|\\_\\_|_| |_|\\__, |   \\_/\\_/ |_| |_|\\___/|___/
                                    |___/
                         _     _
 _ __   __ _ _   _  __ _| |__ | |_ _   _
| '_ \\ / _` | | | |/ _` | '_ \\| __| | | |
| | | | (_| | |_| | (_| | | | | |_| |_| |
|_| |_|\\__,_|\\__,_|\\__, |_| |_|\\__|\\__, |
                   |___/           |___/

""")

println("Executing checks on root directory ${new File('.').absolutePath}")

def os = System.getProperty("os.name").toLowerCase()

println("Detected operating system $os")

def process = os.indexOf("win") >= 0 ? "cmd /c gradle clean build" : "gradle clean build".execute(null, new File('.'))

process.in.eachLine { line ->
    println(line)
}

process.waitFor()

println("Process exit value was: ${process.exitValue()}")

System.exit(process.exitValue())