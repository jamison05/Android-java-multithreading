classdef stroke_Program1
    %UNTITLED Summary of this class goes here
    %   Detailed explanation goes here
    
    properties
        Program
        X_1
        Y_1
        Pressure_1
        Time_1
    end
    
    methods
        function stroke_constructor = stroke_Program1(program, pressure_1, time_1)
           if nargin > 0 
               stroke_constructor.Program = program;
%                stroke_constructor.X_1=x_1;
%                stroke_constructor.Y_1=y_1;
               stroke_constructor.Pressure_1 = pressure_1;
               stroke_constructor.Time_1 = time_1; 
              
           end
        end
        
        
         

         function obj = set.Program(obj,program)
            obj.Program=program;
         end
           
         function get_X_1 = get.X_1(obj)
            get_X_1 = 6+6+ obj.Time_1;
         end    

   
        
        
end
end
