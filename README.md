# tp-video-stream-converter
Achitecture du TP à réaliser 
<img src="https://user-images.githubusercontent.com/60603990/142059359-9ed9718d-f6ff-41bc-a8dc-b00c057d7712.png" alt="Image de l'architecture du TP à réaliser"/>

# Build 
Se positionner à la racine de projet multi-module et faire la commande suivante: 
<b>mvn clean package docker:build</b>

# Launch
Lancer les services avec la commande suivante: 
<b> docker-compose up -d </b>

# Packages
- <h2><b style="color:red"> data </b></h2>
Ce dossier représente le FS(File Système). Il est lié en tant que volume au conteneur <b> video-stream-conv-worker </b>

- <h2><b style="color:red"> nginx </b></h2>
Ce dossier contient la configuration de <b> nginx </b>

- <h2><b style="color:red"> python-cli </b></h2>
Copiez le dossier <b> python-cli </b> sur la machine du client</br> 
Puis ajoutez les chemins  <b> ./python-cli/status/dist </b> et</br>
<b> ./python-cli/video/dist </b> dans les variables d'environnement(path)
de la machine du client.</br>
Vous pouvez ensuite utiliser les commandes suivantes dans votre terminal</br>
Exemple: video -filename messi_video.mp4 -mode avi </br>
Exemple: status -id 37dbe9d2-65ec-487a-b16b-b726bad71156</br>