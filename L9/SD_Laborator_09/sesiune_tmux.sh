#!/bin/bash

SESSION_NAME="custom_session"

# Creează o sesiune nouă în fundal
tmux new-session -d -s $SESSION_NAME

# Panoul 0: terminal pe jumătatea stângă
tmux split-window -h -t $SESSION_NAME      # împarte pe orizontală
tmux select-pane -t $SESSION_NAME:0.1
tmux split-window -v -t $SESSION_NAME:0.1  # împarte jumătatea dreaptă pe verticală

# Opțional: pornește comenzi în fiecare panou
tmux send-keys -t $SESSION_NAME:0.0 'java -jar spring-cloud-dataflow-server-2.5.4.RELEASE.jar' C-m
tmux send-keys -t $SESSION_NAME:0.1 'java -jar spring-cloud-skipper-server-2.4.3.RELEASE.jar' C-m
tmux send-keys -t $SESSION_NAME:0.2 'java -jar spring-cloud-dataflow-shell-2.5.4.RELEASE.jar' C-m


# Atașează sesiunea
tmux attach -t $SESSION_NAME

