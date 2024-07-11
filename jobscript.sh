#!/bin/bash
echo "Bienvenido a la ciudad $ciudad"
if [ "$agente" == true ]
then
	echo "Preparese para la misión agente"
else
	echo "Disfrute la ciuadad $nombre"
fi
echo "..."
sleep 5
echo "Hasta luego!!"
