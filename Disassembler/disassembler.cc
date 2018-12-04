#include <stdio.h>
#include <string.h>
    int getMask(int,int,int);
    int getSubInt(int,int,int);

    void printAdd(int inst){
    int dr = inst >> 9 & 7;
    int sr1 = inst >> 6 & 7; 
    int bit5 = inst >> 5 & 1;

    if(bit5 == 0){
    int sr2 = inst & 15;
    printf("ADD\tR%X, R%X, R%X\n", dr,sr1,sr2);
                 }
    
    else{
    int signBit = inst >> 4 &1;
    int im = inst & 0x1F;
        if(signBit == 0){
            printf("ADD\tR%X, R%X, #%d\n", dr,sr1,im);
                        }
        else{
           im = im + 0xFFFFFFE0;
            printf("ADD\tR%X, R%X, #%d\n", dr,sr1,im);
            }
                        }

        }//End of printAdd
    
       void printAnd(int inst){
            int dr = inst >> 9 & 7;
            int sr1 = inst >> 6 & 7; 
            int bit5 = inst >> 5 & 1;

            if(bit5 == 0){
            int sr2 = inst & 15;
            printf("AND\tR%X, R%X, R%X\n", dr,sr1,sr2);
                         }
    
            else{
            int signBit = inst >> 4 &1;
            int im = inst & 0x1F;
            if(signBit == 0){
                    printf("AND\tR%X, R%X, #%d\n", dr,sr1,im);
                              }
                else{
                im = im + 0xFFFFFFE0;
                    printf("AND\tR%X, R%X, #%d\n", dr,sr1,im);
                    }
                             }

             }//End of printAnd

        void printBr(int inst, int pc){
            printf("BR");
            int cond = inst >> 9 & 7;

            int signBit = inst >> 8 &1;
            int im = inst & 0x1FF;
            if(signBit == 1){
                im += 0xFFFFFE00;
                    }
            int pcOffset = pc + im;

            switch(cond)
            {
            case 1:
                printf("P");
                break;
            case 2:
                printf("Z");
                break;
            case 3:
                printf("ZP");
                break;
            case 4:
                printf("N");
                break;
            case 5:
                printf("NP");
                break;
            case 6:
                printf("NZ");
                break;
            case 7:
                printf("NZP");
                break;
            default :-
                printf("Invalid\n");
                break;

            }
            printf("\tx%X\n", pcOffset);
        
            }//End of printBr

        void printJmpRet(int inst){
        int reg = inst >> 6 & 7;
        if(reg == 7){
            printf("RET\n");
        }
        else{
            printf("JMP\tR%X\n", reg);
        }
        }//End of printJmpRet

        void printLd(int inst, int pc)
        {
        int dr = inst >> 9 & 7;
        int signBit = inst >> 8 & 1;
        int im = inst & 0x1FF;
        if(signBit == 1){
                im += 0xFFFFFE00;
        }
        int pcOffset = pc + im;
        printf("LD\tR%X, x%X\n", dr, pcOffset);  
      
        }//End of printLd

        void printLdr(int inst){
        int dr = inst >> 9 & 7;
        int br = inst >> 6 & 7;
        int im =  inst & 0x3F;
        int signBit = inst >> 5 & 1;
        if(signBit == 1){
            im += 0xFFFFFFC0;
        }
        printf("LDR\tR%X, R%X, #%d\n", dr, br, im); 

        }//End of printLdr

        void printLdi(int inst, int pc){
        int im = inst & 0x1FF;
        if(inst >> 8 & 1){
            im += 0xFFFFFE00;
        }
        int offSet = pc + im;
        printf("LDI\tR%X, x%X\n", getSubInt(9,11,inst), offSet);
        //int *indirect = &offSet;
        //printf("INDIRECT: %X\n", &indirect);

        }//End of printLdi

        void printLea(int inst, int pc){
        int im = inst & 0x1FF;
        if(inst >> 8 & 1){
            im += 0xFFFFFE00;
        }
        int offSet = pc + im;
        printf("LEA\tR%X, x%X\n", getSubInt(9,11,inst), offSet);
        }//End of printLea

        void printSt(int inst, int pc){
         int sr = inst >> 9 & 7;
        int signBit = inst >> 8 & 1;
        int im = inst & 0x1FF;
        if(signBit == 1){
                im += 0xFFFFFE00;
        }

        int pcOffset = pc + im;
        printf("ST\tR%X, x%X\n", sr, pcOffset);  
        }//End of printSt
    
        void printStr(int inst){
        int sr = inst >> 9 & 7;
        int br = inst >> 6 & 7;
        int im =  inst & 0x3F;
        int signBit = inst >> 5 & 1;
        if(signBit == 1){
            im += 0xFFFFFFC0;
        }
        printf("STR\tR%X, R%X, #%d\n", sr, br, im); 

        }//End of printStr

        void printSti(int inst, int pc){
        int im = inst & 0x1FF;
        if(inst >> 8 & 1){
            im += 0xFFFFFE00;
        }
        int offSet = pc + im;
        printf("STI\tR%X, x%X\n", getSubInt(9,11,inst), offSet);
            }//End of printSti

        void printNot(int inst){printf("NOT\tR%X, R%X\n",(inst >> 9 & 7), (inst >> 6 & 7));}
        
        void printRti(int inst){printf("RTI\n");}

        void printTrap(int inst){
        int vector = inst & 15;
        switch(vector)
        {
        case 0 :
                printf("GETC\n");                
                break;
        case 1 :
                printf("OUT\n");                 
                break;
        case 2 :
                printf("PUTS\n");                  
                break;
        case 3 :
                printf("IN\n");                 
                break;
        case 4 :
                printf("PUTSP\n");                  
                break;
        case 5 :
                printf("HALT\n");                  
                break;
        default :
                printf("Invalid Trap\n");
                break;
        }
            
        }//End of printTrap

    int getMask(int start,int end, int bin){
	    if((bin >1) ||(bin < 0) || (start > end) || (start > 31) || (start < 0) || (end > 31) || (end < 0))
		    {return 0;}
	    int mask = 0;
	    if(bin == 1){
		    for(int i = start;i < (end +1);i++){
		    mask += (1 << i);
		    }}
  	    else{
	    mask = 0xFFFFFFFF;
		    for(int j = start;j < (end +1);j++){
		    mask -= (1 << j);
		    }}	
	    return mask;	}
    

    int getSubInt(int start, int stop, int value){
    	if((start > stop) || (start > 31) || (stop > 31) || (start < 0) || (stop < 0))
        	{return 0;}
    	unsigned int x = 0;
    	
        if(start == 0){
    	x = getMask(start,stop,1) & value;
                   	}
	    else{
	    x = (value>>(start)) & getMask(0, (stop-start),1);
	                }
                                    	return x;}


    void printJsrJsrr(int inst, int pc){
    
    int f = inst >> 11 & 1;
    if(f == 1){
    //JSR
    int im =  inst & 0x7FF;
    if(inst >> 10 & 1){
    im += 0xFFFFF800;
            }
    int offSet = pc + im;
    printf("JSR\tx%X\n", offSet);
                }
    else{
        printf("JSRR\tR%X\n", getSubInt(6,8,inst));
        }
    }

	void printAssembly(char file[]){

	FILE *infile;
	int inst = 0;
	char buff[4];
	infile = fopen(file,"r");
	if(infile == NULL)
		printf("Couldn't open the file");

	int flag = 5;
	int PC = 0;
	while(fscanf(infile, "%x", &inst) != -1)
	{
        int opcode = inst >> 12 & 15;
        
		if(flag == 5){
	  	//Starting Memory address	
		//printf("%x-First\n", inst);
		flag = 0;
		PC = inst;
		}
        else{
            PC++;
            switch(opcode){
            case 0 :
                printBr(inst, PC);
                break;
		    case 1 :
                printAdd(inst);
                break;
            case 2 :
                printLd(inst, PC);
                break;
            case 3 :
                printSt(inst, PC);
                break;
            case 4 :
                printJsrJsrr(inst,PC);
                break;
            case 5 :
                printAnd(inst);
                break;
            case 6 :
                printLdr(inst);
                break;
            case 7 :
                printStr(inst);
                break;
            case 8 :
                printRti(inst);
                break;
            case 9 :
                printNot(inst);
                break;
            case 10 :
                printLdi(inst, PC);
                break;
            case 11 :
                printSti(inst, PC);
                break;
            case 12 :
                printJmpRet(inst);
                break;
            case 13 :
                printf("\n");
                break;
            case 14 :
                printLea(inst,PC);
                break;
            case 15 :
                printTrap(inst);
                break;
            default :
                printf("Invalid Instruction\n");
                break;  
		}
        }
		
	}	
	fclose(infile);
	}
int main(){
char filename[] = "test2.hex";
printAssembly(filename);
}
