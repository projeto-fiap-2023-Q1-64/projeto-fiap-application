#!/bin/bash

cd entrega/kubernetes || exit

# stop
sh ./infra/stop.sh

# metrics server
echo "Aplicando servidor de metricas"
kubectl apply -f metrics.yaml

# start db
echo "Iniciando db"
kubectl apply -f db-secret.yaml
kubectl apply -f db-pod.yaml
kubectl apply -f db-service.yaml

# start app
echo "Iniciando app"
kubectl apply -f app-secret.yaml
kubectl apply -f app-deployment.yaml
kubectl apply -f app-service.yaml
kubectl apply -f app-hpa.yaml

echo "Inicializacao concluida"