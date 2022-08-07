from django.shortcuts import render

def login(request):
   return render(request,'login.html')




def ViewAddMovie(request):
    return render(request,'AddMovie.html')



def createMovie(request):
           
    if request.method == 'POST': 
        
        rut = request.POST.get('rut') 
        nombres = request.POST.get('nombre')
        apellidos = request.POST.get('apellido')
        celular= request.POST.get('celular')
        tipousuario=request.POST.get('tipousuario')
        Correo=request.POST.get('correo')
        nombre_usuario=nombres[:2] +"."+ apellidos[:2]
        password = UserSalud.objects.make_random_password(length=6, allowed_chars="abcdefghjkmnpqrstuvwxyz01234567889")
        
       
       

        
        

    return 2 