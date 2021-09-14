#include <iostream>
#include <unistd.h> // define o fork
#include <sys/types.h> // define o pid
#include <time.h>

using namespace std;
int main(){

  pid_t idProcesso;
  idProcesso = fork();

  switch(idProcesso){
    case -1:
      exit(1);
    case 0:
      sleep(22); // idade do pai ao nascer o filho 1
      cout<<"Nasce o filho 1  ID :"<<getpid()<<" / Pai ID :"<<getppid()<<endl;
      idProcesso = fork();
      if(idProcesso == 0){
        sleep(16); // idade do filho 1 ao nascer o neto 1
        cout<<"Nasce o neto 1  ID :"<<getpid()<<" / Pai ID :"<<getppid()<<endl;
        idProcesso = fork();
        if(idProcesso == 0){
            sleep(30); // 68 - 38  , idade em que o pai se torna bisavô menos idade em que ele se torna avô
            int id = getppid(); // como o neto 1 morre primeiro que o bisneto , foi necessário guardar seu
            // id para imprimir no bisneto
            cout<<"Nasce o bisneto ID :"<<getpid()<<" / Pai ID :"<<getppid()<<endl;
            sleep(12); // idade em que morre o bisneto
            cout<<"Morre o bisneto ID :"<<getpid()<<" / Pai ID :"<<id<<endl;
            exit(0);
        }
        else{
          sleep(35); // idade em que morre o neto 1
          cout<<"Morre o neto 1  ID :"<<getpid()<<" / Pai ID :"<<getppid()<<endl;
          exit(0);
        }
      }
      else{
        sleep(61); // idade em que morre o filho 1
        cout<<"Morre o filho 1  ID :"<<getpid()<<" / Pai ID :"<<getppid()<<endl;
        exit(0);
      }
      break;
    default:
      cout<<"Nasce o pai ID :"<<getpid()<<endl;
      idProcesso = fork();
      if(idProcesso == 0){
        sleep(25); // idade do pai ao nascer o filho 2
        cout<<"Nasce o filho 2  ID :"<<getpid()<<" / Pai ID :"<<getppid()<<endl;
        idProcesso = fork();
        if(idProcesso == 0){
            sleep(20); // 25 + 20 = 45 idade do pai quando nasce o neto 2
            cout<<"Nasce o neto 2  ID :"<<getpid()<<" / Pai ID :"<<getppid()<<endl;
            sleep(33); // idade em que morre o neto 2
            cout<<"Morre o neto 2  ID :"<<getpid()<<" / Pai ID :"<<getppid()<<endl;
            exit(0);
        }
        else{
          sleep(55); // idade em que more o filho 2
          cout<<"Morre  o filho 2  ID :"<<getpid()<<" / Pai ID :"<<getppid()<<endl;
          exit(0);
        }
      }
      else{
        idProcesso = fork();
        if(idProcesso == 0){
            sleep(32); // idade do pai ao nascer o filho 3
            cout<<"Nasce o filho 3  ID :"<<getpid()<<" / Pai ID :"<<getppid()<<endl;
            sleep(55); // idade em que morre o filho 3
            cout<<"Morre o filho 3  ID :"<<getpid()<<" / Pai ID :"<<getppid()<<endl;
            exit(0);
        }
        else{
          sleep(90); // idade em que morre o pai
          cout<<"Morre o pai ID :"<<getpid()<<endl;
          exit(0);
        }
      } 
  } // fim do switch
  return 0;
}