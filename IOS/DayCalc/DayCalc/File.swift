//
//  File.swift
//  DayCalc
//
//  Created by Shilpa on 4/28/16.
//  Copyright © 2016 Shilpa. All rights reserved.
//

import Foundation

class DayCalculator {
    
    //Set the days array
    let days: [Int] = [0,31,28,31,30,31,30,31,31,30,31,30,31]
    
    //calculate the leap year
    func leap(m:Int, y:Int) -> Int {
        
        if m == 2 && y % 4 == 0 {
            if y % 100 == 0 && y % 400 != 0 {
                return 0
            }
            return 1
        }
        return 0
    }
    
    //Check if the days are valid
    func validate(day1:Int, day2:Int, month1:Int, month2:Int, year1:Int, year2:Int) -> String {
        
        if month1 < 1 || month1 > 12 || month2 < 1 || month2 > 12 {
            return "Enter a valid month"
        }
        
        if year1 < 1582 || year2 < 1582 {
            return "Enter a valid year greater than 1582"
        }
        
        if year1 > year2 {
            return "Enter the second date greater than the first"
        }
        
        if year1 == year2 && month1 > month2 {
            return "Enter the second date greater than the first"
        }
        
        if year1 == year2 && month1 == month2 && day1 > day2 {
            return "Enter the second date greater than the first"
        }
        
        if day1 < 0 || day1 + leap(month1, y: year1) > days[month1] || day2 < 0 || day2 + leap(month2, y: year2) > days[month2] {
            return "Enter a valid day"
        }
        return "Valid dates"
    }
    
    //Function to calculate the difference between the two days
    func calDifference(var d1: Int,var m1: Int,var y1: Int,var d2: Int,var m2: Int,var y2: Int) -> Int {
        
        //Initialise the dayscount to zero
        var daysCount: Int = 0
        
        //Check if both the dates are equal, if not increment the days by one until the second date is reached.
        while (!(d1 == d2 && m1 == m2 && y1 == y2)){
            //Increment the day
            d1 = d1 + 1
            
            //If the days is greater than the actual days, increment the month
            while d1 > (days[m1] + leap(m1,y: y1)) {
                
                d1 = d1 - (days[m1] + leap(m1,y: y1))
                
                m1 = m1 + 1
                
                //If month is greater than 12, increment the result.
                if m1 > 12 {
                    m1=m1-12
                    y1 = y1 + 1
                }
            }
            //Calculate the days count
            daysCount = daysCount + 1
            
        }
        //Return the daysCount
        return daysCount
    }
    
    
}

