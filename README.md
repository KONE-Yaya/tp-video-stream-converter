# tp-video-stream-converter
<h3><b>Achitecture du TP à réaliser </b></h3></br>

<img src="https://user-images.githubusercontent.com/60603990/142059359-9ed9718d-f6ff-41bc-a8dc-b00c057d7712.png" alt="Image de l'architecture du TP à réaliser"/>

# Build 
Se positionner à la racine de projet multi-module et faire la commande suivante: 
<b>mvn clean package docker:build</b></br>
Cette commande permettra de générer les images docker des packages:</br>
<b>video-stream-api</b>, <b>video-stream-conv-server</b>, <b>video-stream-core</b> et <b>video-stream-conv-worker</b>

# Launch
Lancer les services avec la commande suivante: 
<b> docker-compose up -d </b>

# Packages
- <h2><b style="color:red"> data </b></h2>
Ce dossier représente le FS(File Système). Il est lié en tant que volume au conteneur <b> video-stream-conv-worker </b></br>
Il contient donc les videos à convertir et les videos qui ont été converti.
Après conversion, la vidéo converti reprend le nom de la video source et puis 
  <b> _converted </b> à la fin du nom.

- <h2><b style="color:red"> nginx </b></h2>
Ce dossier contient la configuration de <b> nginx </b>

- <h2><b style="color:red"> python-cli </b></h2>
Copiez le dossier <b> python-cli </b> sur la machine du client</br> 
Puis ajoutez les chemins  <b> ./python-cli/status/dist </b> et</br>
<b> ./python-cli/video/dist </b> dans les variables d'environnement(path)
de la machine du client.</br>
Vous pouvez ensuite utiliser les commandes suivantes dans votre terminal</br></br>
<b>Exemple</b>: <b style="color:red">video -filename messi_video.mp4 -mode avi</b> </br>
<b>Exemple</b>: <b style="color:red">status -id 37dbe9d2-65ec-487a-b16b-b726bad71156</b></br></br>

<b style="color:yellow">Warning:</b> Il se peut que vous ne trouvez pas les fichiers <b>video.exe</b> et <b>status.exe</b>
dans les dossiers respectifs(<b> ./python-cli/video/dist </b> et <b> ./python-cli/status/dist </b>) car votre antivirus peut le supprimer. Il va falloir les générer de la manière suivante à partir de votre terminal:</br>
<b>1</b>- <b><i>pip install pyinstaller</i></b></br>
<b>2</b>- <b><i>pyinstaller --onfile video.py</i></b> (étant positionné dans le repertoire <b> ./python-cli/video</b> pour générer video.exe )</br>
<b>3</b>- <b><i>pyinstaller --onfile status.py</i></b> (étant positionné dans le repertoire <b> ./python-cli/status</b> pour générer status.exe )</br>


# Team Members
- <b style="color:blue"> DONFACK Jeagni Anaelle</b> 
- <b style="color:blue">KAMTA Nana Merveille Lornelle</b>
- <b style="color:blue">KONE Yaya N'golo</b>