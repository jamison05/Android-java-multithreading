fid = fopen('C:\Users\Joshua\Documents\Music_Programming\Input_Matlab\M1.txt');
fid3= fopen('C:\Users\Joshua\Documents\Music_Programming\Input_Matlab\stopp.txt');
fid4= fopen('C:\Users\Joshua\Documents\Music_Programming\Input_Matlab\mjtsm1.txt');
tm = ones(100,8);
tline3= fgetl(fid3);
t2m = ones(100,5);
time_1 = ones(100,1);
note_t= ones(100,1);
global_counter = 0;
musicstring = cell(500,1);


iter=0;

tm = ones(100,8);

fid3= fopen('C:\Users\Joshua\Documents\Music_Programming\Input_Matlab\stopp.txt');
fid4= fopen('C:\Users\Joshua\Documents\Music_Programming\Input_Matlab\mjtsm1.txt');
tline3= fgetl(fid3);
tline4= fgetl(fid4);




global_counter= global_counter +1;

file_string= 'C:\Users\Joshua\Documents\Music_Programming\Input_Matlab\m';
file_str= strcat(file_string,num2str(global_counter), '.txt');

fid = fopen(file_str);


%I will delete the time stamp for flowstone it does not work and I am
%goning to put the time stamp with in the loop condition. 
tline = fgetl(fid);


while ischar(tline)
iter= iter +1;
    musicstring(iter) = {tline};

    tline = fgetl(fid);



end

for i = (1:iter)
mutstring =char(musicstring(i));
mt1= strsplit(mutstring, ',');
mt1= (strrep(mt1, '"', ''));
mt1= (strrep(mt1, '[', ''));
mt1= (strrep(mt1, ']' , ''));
jt= cell2mat(mt1);
js= str2num(jt);
tm(i,:)= js;

file_name= strcat('musicfile', num2str(global_counter), '.mat');
save(file_name, 'tm');
iter=0;

end

fclose(fid3);
fclose(fid4);




fclose(fid);


for j = (1:100)
x1= tm(j,6);
x2= tm(j,7);
x3= tm(j,8);
x1= (x1/60)*1200;
x2= x2;
x3= (x3/1000);
combined= x1+ x2+ x3;
time_1(j)= combined;
end
for k = (1:100)
if tm(k, 1) == 148  
    tm(k, 1)= 1000;
end
 for j = ((k+1):100)   
    if tm(k,3)== tm(j, 3) && tm(j,1) == 128
        note_t(j)=time_1(k)-time_1(j);
    end     
 end
end




%Array of the note pitch, velocity, and duration. 



% You will make an array of note values and timing and velocity.  

