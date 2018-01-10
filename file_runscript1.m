%a1 - acrylicbrush
%Here I am going to put time into 
fileID = fopen('scripttext_A2.dat','w');
formatSpec = '%s';


[nrows,ncols] = size(thickacrylicbristle);
        %pnt x  434.00 y  244.00 time 124282 prs 1.00 tlt 0.00 brg 0.00 whl 1.00 rot 0.00
        time =3; routine= 3; option =3;
        transfer_value = drawf1(time, routine,option);

        %There is not a direct way to create hardcoded spaces to strings without
        %sending it to a function.  
   
   location= cell(22,1);    
   for i = 1:20   
        current_str = strcat('pnt x',char('-----'), num2str(transfer_value(1)),char('-----'), 'y', ...
        char('-----'), num2str(transfer_value(2)),char('-----'),'time ',char('-----'), num2str(time),char('-----'), 'prs',' ', num2str(1),char('-----'), ...
        ' tlt   0.00   brg   0.00   whl   1.00   rot   0.00');
        updatedString = regexprep(current_str, '-----', '      ');
       location(i)=cell({updatedString}); 
    end
        %Here is the intro function

%Cat concatenates the array along the first dimension. 
result = cat(1,thickacrylicbristle,location);   
T0=cell2table(intro_script, 'VariableNames',{'var1'});  
%Change the functions withiin the script with key variables.
%So there are numerous functions for the equations.  

T = cell2table(result, 'VariableNames',{'var1'});
writetable(T, 'script_as_table.txt');
%The following code is where you can add 
thickacrylicbristle(19)=cell({' '});

%for row = 1:nrows
 %   fprintf(fileID,formatSpec,thickacrylicbristle{row,:},'');
%end


%The last part will be stroke, time, and pressue.  So what I am going to do
%is create the functions.

%Add the first initial code then the movements, will rehash the files 
% s1 = 'value';
% s2 = num2str(operated_value);
% s = strcat(s1,s2)




%Will have to create different function files. 

fclose(fileID);