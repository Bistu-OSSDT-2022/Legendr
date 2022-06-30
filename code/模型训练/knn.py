knn_acc = []
m=0
for i in range(1,20):
    neigh = KNN(n_neighbors=i,algorithm='auto',weights='distance')
    neigh.fit(train_images,train_labels)
    y_pred = neigh.predict(test_images)
    print(metrics.adjusted_rand_score(test_labels,y_pred))
    if metrics.adjusted_rand_score(test_labels,y_pred)>m:
        m=metrics.adjusted_rand_score(test_labels,y_pred)
        model_path = './neigh_model'
        joblib.dump(neigh,model_path)
    knn_acc.append(metrics.accuracy_score(test_labels,y_pred))