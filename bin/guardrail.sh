#!/bin/bash

export GUARDRAIL_HOME=$(cd `dirname $0`/..; pwd)

# Configures Java
setup_java() {
    JAVA=$(command -v java)
    if [[ -z "$JAVA" ]]; then
        if [[ -x "$JAVA_HOME/bin/java" ]]; then
            JAVA="$JAVA_HOME/bin/java"
        fi

        if [ ! -x "$JAVA" ]; then
            echo "Couldn't find java - set JAVA_HOME or add java to the path"
            exit 1
        fi
    fi

    # JVM parameters
    JVM_PARAMS="-Dlogging.config=$GUARDRAIL_HOME/etc/logback.xml"
    JVM_PARAMS+=" -DLOG_LOCATION=$GUARDRAIL_HOME/logs"
}

# Application status
status() {
    if pgrep -f "java -jar.*guardrail" > /dev/null
    then
        echo "guardrail is running..."
    else
        echo "guardrail not running..."
    fi
}

# Starts the application
start() {
    if pgrep -f "java -jar.*guardrail" > /dev/null
    then
        echo "guardrail is already running..."
    else
        setup_java

        echo -n "Starting guardrail..."

        exec ${JAVA} \
            -jar \
            ${JVM_PARAMS} \
            ${GUARDRAIL_HOME}/lib/guardrail*.jar \
            --dbPath=${GUARDRAIL_HOME}/../db.json &

        echo "started(`pgrep -f 'java -jar.*guardrail'`)"
    fi
}

# Stops the application
stop() {
    echo "Stopping guardrail..."
    pkill -f 'java -jar.*guardrail'
}

if [[ ! -z $1 ]]; then
    case $1 in
        "start")
            start
            ;;
        "stop")
            stop
            ;;
        "restart")
            stop
            sleep 1
            start
            ;;
        "status")
            status
            ;;
        *)
            echo "Invalid argument provided: $1"
            exit 1
            ;;
    esac
else
    echo "No arguments provided (expected 'start', 'stop', or 'restart')!"
fi