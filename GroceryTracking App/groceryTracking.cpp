/*
 * groceryTracking.cpp
 *
 *  Date: [2022-06-18]
 *  Author: [Michael Reynolds]
 */

#include <Python.h>
#include <iostream>
#include <Windows.h>
#include <cmath>
#include <cctype>
#include <algorithm>
#include <string>
#include <ctype.h>
#include <fstream>


using namespace std;

/* 
	DisplayMenu
	
	@Brief Prints a prompting main menu with 4 options
		Option 1: Multiplication Table for input
		Option 2: Multiplication Table for input
		Option 3: Double input value
		Option 4: Quit Application

	@return void	
*/
void DisplayMenu() 
{
	//	Prints the prompting main menu with 4 options
	cout << endl << endl << "----------MAIN MENU----------" << endl << endl;
	cout << "1: Display Grocery List of Given Day" << endl;
	cout << "2: Display Purchase Count of Item of Given Day" << endl;
	cout << "3: Display Grocery Chart of Given Day" << endl;
	cout << "4: Exit Application" << endl << endl;
	cout << "Enter your selection as a number 1, 2, 3, or 4." << endl << endl;
}

/*	
*	CallProcedure
* 
	@Brief This function will take in a Python function name, and execute it.

	To call this function, simply pass the function name in Python that you wish to call.
	@Param pName String Name of the Python function to call
	@Return None
*/
void CallProcedure(string pName)
{

	char* procname = new char[pName.length() + 1];
	std::strcpy(procname, pName.c_str());

	Py_Initialize();
	PyObject* my_module = PyImport_ImportModule("groceryTracking");
	PyErr_Print();
	PyObject* my_function = PyObject_GetAttrString(my_module, procname);
	PyObject* my_result = PyObject_CallObject(my_function, NULL);
	Py_Finalize();

	delete[] procname;
}

/*
*	callIntFunc
* 
	@Brief To call this function, pass the name of the Python function you wish to call and the string parameter you want to send

	@Param proc String Name of the Python function to call
	@Param param String Parameter to pass into the Python Function
	@Return output integer from Python Function
*/
int callIntFunc(string proc, string param)
{
	char* procname = new char[proc.length() + 1];
	std::strcpy(procname, proc.c_str());

	char* paramval = new char[param.length() + 1];
	std::strcpy(paramval, param.c_str());


	PyObject* pName, * pModule, * pDict, * pFunc, * pValue = nullptr, * presult = nullptr;
	// Initialize the Python Interpreter
	Py_Initialize();
	// Build the name object
	pName = PyUnicode_FromString((char*)"groceryTracking");
	// Load the module object
	pModule = PyImport_Import(pName);
	// pDict is a borrowed reference 
	pDict = PyModule_GetDict(pModule);
	// pFunc is also a borrowed reference 
	pFunc = PyDict_GetItemString(pDict, procname);
	if (PyCallable_Check(pFunc))
	{
		pValue = Py_BuildValue("(z)", paramval);
		PyErr_Print();
		presult = PyObject_CallObject(pFunc, pValue);
		PyErr_Print();
	}
	else
	{
		PyErr_Print();
	}
	//printf("Result is %d\n", _PyLong_AsInt(presult));
	Py_DECREF(pValue);
	// Clean up
	Py_DECREF(pModule);
	Py_DECREF(pName);
	// Finish the Python Interpreter
	Py_Finalize();

	// clean 
	delete[] procname;
	delete[] paramval;


	return _PyLong_AsInt(presult);
}

/*
*	callIntFunc int Overload
* 
	@Brief To call this function, pass the name of the Python function you wish to call and the int parameter you want to send

	@Param proc String Name of the Python Function to call
	@Param param Int integer parameter for the Python Function being called

	@Return integer returned by the Python Function being called
*/
int callIntFunc(string proc, int param)
{
	char* procname = new char[proc.length() + 1];
	std::strcpy(procname, proc.c_str());

	PyObject* pName, * pModule, * pDict, * pFunc, * pValue = nullptr, * presult = nullptr;
	// Initialize the Python Interpreter
	Py_Initialize();
	// Build the name object
	pName = PyUnicode_FromString((char*)"groceryTracking");
	// Load the module object
	pModule = PyImport_Import(pName);
	// pDict is a borrowed reference 
	pDict = PyModule_GetDict(pModule);
	// pFunc is also a borrowed reference 
	pFunc = PyDict_GetItemString(pDict, procname);
	if (PyCallable_Check(pFunc))
	{
		pValue = Py_BuildValue("(i)", param);
		PyErr_Print();
		presult = PyObject_CallObject(pFunc, pValue);
		PyErr_Print();
	}
	else
	{
		PyErr_Print();
	}
	//printf("Result is %d\n", _PyLong_AsInt(presult));
	Py_DECREF(pValue);
	// Clean up
	Py_DECREF(pModule);
	Py_DECREF(pName);
	// Finish the Python Interpreter
	Py_Finalize();

	// clean 
	delete[] procname;

	return _PyLong_AsInt(presult);
}


/*
*	DisplayList
* 
	@Brief Calls on Python function "printList" to display entire grocery list with item count

	@Return void
*/
void DisplayList() 
{
	//Call Python function "printList"
	CallProcedure("printList");
}

/*
*	DisplayItemCount
* 
	@Brief Calls on Python function "countSpecificItem" to display user input item name and its count for purchases in list

	@Param itemName String name of item to print with info
	@Return void
*/
void DisplayItemCount(string itemName) 
{

	//Make string lowercase and capitalize the first letter
	transform(itemName.begin(), itemName.end(), itemName.begin(), [](unsigned char c) {return tolower(c); });
	itemName[0] = toupper(itemName[0]);

	//Call Python function "countSpecificItem" and initialize return in variable
	int itemCount = callIntFunc("countSpecificItem", itemName);

	//Print item:qty
	cout << endl << "Item: " << itemName << " Qty Purchased: " << itemCount << endl << endl;
}


/*
*	LeadingSpaces
* 
	@Brief Add leading spaces for histogram
	@Return void
*/ 
void LeadingSpaces(int cnt)
{
	for (auto i{ cnt };i > 0;i--)
		cout << " ";
}

/*
*	DisplayChart
* 
	@Brief Print histogram of Item and Quantity Purchased

	@Return void
*/
void DisplayChart() 
{
	//Call Python function "writeFrequency" to write frequency.dat file with grocery list info
	CallProcedure("writeFrequency");

	ifstream fStream;
	string line;

	//Open data file
	fStream.open("frequency.dat");
	
	
	if (fStream.is_open()) 
	{
		//While next line available, print item and count in "*"
		while (getline(fStream, line)) 
		{
			//Split line into itemName:Qty
			string delimiter = " ";
			string histoCount;
			int spacingCounter = 15;

			string itemName = line.substr(0, line.find(delimiter));
			line.erase(0, line.find(delimiter) + delimiter.length());

			//For itemQty add stars needed for histogram
			int itemQty = stoi(line.substr(0, line.find(delimiter)));
			for (auto i{ itemQty }; i > 0; i--) 
			{
				histoCount.append("*");
			}

			cout << itemName;
			LeadingSpaces(spacingCounter - itemName.length());
			cout << histoCount << endl;
		}

		cout << endl;
	}

	//Close data file
	fStream.close();
}

int main()
{
	//Variables for menu choice, input number, output result
	int menuChoice = 0;
	string itemName;

	//Check for exit application choice
	while (menuChoice != 4)
	{
		//Display Main Menu
		DisplayMenu();

		//Choose Menu Option
		cin >> menuChoice; cout << endl;

		//Handle choices
		switch (menuChoice) 
		{
			case 1:

				//Display List
				DisplayList();

				system("pause");
				break;

			case 2:

				//Display specific item by item name
				cout << endl << "Select Item Name: " << endl;

				while (true) {
					
					cin >> itemName;

					if (!cin.fail()){
						break;
					}
					else
					{
						cout << "Enter A Valid Item Name (Text)!\n";
						cin.ignore(cin.rdbuf()->in_avail(), '\n');
					}
				}

				//Display Item Count
				DisplayItemCount(itemName);

				system("pause");
				break;

			case 3:

				//Display Chart
				DisplayChart();

				system("pause");
				break;

			case 4:

				//Exit Application
				cout << "Exiting Application..." << endl << endl;

				system("pause");
				break;

			default:

				//On Invalid input prompt main menu again
				cout << "Invalid Input! Try Again..." << endl << endl;
				cin.clear();
				cin.ignore(cin.rdbuf()->in_avail(), '\n');

				system("pause");
				break;
		}

	}


	return 0;
}