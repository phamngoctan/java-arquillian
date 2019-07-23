# KLARA Kubernetes testrun

## How to run 

GET http://localhost:8081/java-arquillian-0.0.1-SNAPSHOT/resources/employees

GET http://localhost:8081/java-arquillian-0.0.1-SNAPSHOT/resources/version
`{"java-arquillian":"0.0.1-SNAPSHOT"}`

## How to build docker image & push to google cloud repository
docker build -t eu.gcr.io/klara-tryout/hello_world:vN .                  ----- increment N
docker push eu.gcr.io/klara-tryout/hello_world:vN

docker build -t eu.gcr.io/klara-tryout/hello_world:v1 .
docker push eu.gcr.io/klara-tryout/hello_world:v1

## How to deploy to k8s

kubectl run helloworld --image=eu.gcr.io/klara-tryout/hello_world:vN --port 8080 --namespace=tan
kubectl run hello-world --image=eu.gcr.io/klara-tryout/hello_world:v1 --port 8080 --namespace=tan

kubectl apply -f k8s.yaml
kubectl delete -f k8s.yaml

kubectl delete pod hello-world-5f88c99975-8h8mt --namespace=tan
kubectl delete service hello-world-service --namespace=tan
kubectl delete deployment hello-world --namespace=tan

kubectl log -f hello-world-pod --namespace=tan

kubectl get deployment --namespace=tan
kubectl get service --namespace=tan