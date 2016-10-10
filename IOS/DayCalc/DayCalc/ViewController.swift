//
//  ViewController.swift
//  DayCalc
//
//  Created by Shilpa on 4/28/16.
//  Copyright © 2016 Shilpa. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    //Obtain the refrences to the different views
    @IBOutlet weak var Day1: UITextField!
    @IBOutlet weak var Month1: UITextField!
    @IBOutlet weak var Year1: UITextField!
    @IBOutlet weak var Day2: UITextField!
    @IBOutlet weak var Month2: UITextField!
    @IBOutlet weak var Year2: UITextField!
    
    @IBOutlet weak var Result: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //add the handler to calculate button
        CalButton.addTarget(self, action: "onCalButtonClicked:", forControlEvents: .TouchUpInside)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBOutlet weak var CalButton: UIButton!
    
    //Function to be called on the click of the button
    func onCalButtonClicked(sender:UIButton!) {
        print("Button Clicked")
        
        //create an object of the class
        var obj = DayCalculator()
        
        //check if all the fields are entered
        if (Day1.text?.isEmpty)! || (Day2.text?.isEmpty)!{
            displayError("All fields are compulsory")
            return
        }
        
        if (Month1.text?.isEmpty)! || (Month2.text?.isEmpty)! {
            displayError("All fields are compulsory")
            return
        }
        
        if (Year1.text?.isEmpty)! || (Year2.text?.isEmpty)! {
            displayError("All fields are compulsory")
            return
        }
        
        //Check if all the values are valid integers
        var status : String = ""
        
        //validate fields for numeric values
        if let value = Int(Month1.text!) {
        }else{
            status = "Enter a valid month"
        }
        
        if let value = Int(Month2.text!) {
        }else{
            status = "Enter a valid month"
        }
        
        if let value = Int(Day1.text!) {
           
        }else{
            status = "Enter a valid day"
        }
        
        if let value = Int(Day2.text!) {
           
        }else{
            status = "Enter a valid day"
        }
        
        if let value = Int(Year1.text!) {
          
        }else{
            status = "Enter a valid year"
        }
        
        if let value = Int(Year2.text!) {
          
        }else{
            status = "Enter a valid year"
        }
        
        //Display error status
        if status != "" {
            displayError(status)
            return
        }
    
        //Check the length of date, month and year
        if Day1.text?.characters.count > 2 || Day2.text?.characters.count > 2{
                status = " Day cannot exceed two digits"
        }
    
        if Month1.text?.characters.count > 2 || Month2.text?.characters.count > 2 {
                status = " Month cannot exceed two digits"
        }
    
        if Year1.text?.characters.count > 4 || Year2.text?.characters.count > 4 {
        status = " Year cannot exceed four digits"
        }
        
        //Display error status
        if status != "" {
            displayError(status)
            return
        }
    
        //Call teh validate method to check if the entered date is valid
        var returnValue = obj.validate(Int(Day1.text!)!, day2: Int(Day2.text!)!, month1: Int(Month1.text!)!, month2: Int(Month2.text!)!, year1: Int(Year1.text!)!, year2: Int(Year2.text!)!)
        
        //If not valid display the error
        if returnValue != "Valid dates" {
            displayError(returnValue)
            return
        }
        
        //Calculate the difference in the number of days between the two given dates
        var dateDiff = obj.calDifference(Int(Day1.text!)!, m1: Int(Month1.text!)!, y1: Int(Year1.text!)!, d2: Int(Day2.text!)!, m2: Int(Month2.text!)!, y2: Int(Year2.text!)!)
        
        //Display the result
        print(dateDiff)
        
        //Display the result in the textfield
        Result.text = String(dateDiff)
        
    }
    
    //Diaply the error message in teh textField
    func displayError(message: String){
        
        Result.text = message
        
    }

}

