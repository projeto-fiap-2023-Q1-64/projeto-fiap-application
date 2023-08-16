#!/bin/bash

cd entrega/kubernetes || exit

# remove app
echo "Removendo app"
kubectl delete deployment projetofiap-deployment
kubectl delete service svc-app-projetofiap
kubectl delete hpa projetofiap-hpa
kubectl delete secret opaque-app-secret

# remove db
echo "Removendo db"
kubectl delete pod projeto-fiap-db
kubectl delete service svc-db-projetofiap
kubectl delete secret opaque-postgres-secret

echo "Stop concluido"