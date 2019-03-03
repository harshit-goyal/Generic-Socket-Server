cd dist
rd /q/s com
cd..
xcopy resources\*.* dist/s/y
xcopy classes\*.* dist/s/y
cd dist
jar -cvf TMSocketFramework-v1.0.jar com images
cd..
