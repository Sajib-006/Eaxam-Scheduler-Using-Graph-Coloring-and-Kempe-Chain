#include<bits/stdc++.h>
using namespace std;
int main(){
    cout<<"hi";
    string input_stu = "yor-f-83.stu";
    string input_crs = "yor-f-83.crs";
    string myText;
    ifstream fin_stu,fin_crs;

    fin_stu.open(input_stu);
    while (getline (fin_stu, myText)) {
      cout << myText<<endl;
    }
    fin_stu.close();


    fin_crs.open(input_crs);
    string lastLine;
    bool keepLooping = true;
    if(fin_crs.is_open()) {
        fin_crs.seekg(-1,ios_base::end);                // go to one spot before the EOF
        getline(fin_crs,lastLine);
        cout << "Result: " << lastLine << '\n';
        while(keepLooping) {
            char ch;
            fin_crs.get(ch);                            // Get current byte's data

            if((int)fin_crs.tellg() <= 1) {             // If the data was at or before the 0th byte
                fin_crs.seekg(0);                       // The first line is the last line
                keepLooping = false;                // So stop there
            }
            else if(ch == '\n') {                   // If the data was a newline
                keepLooping = false;                // Stop at the current position.
            }
            else {                                  // If the data was neither a newline nor at the 0 byte
                fin_crs.seekg(-2,ios_base::cur);        // Move to the front of that data, then to the front of the data before it
            }
        }


        getline(fin_crs,lastLine);                      // Read the current line
        cout << "Result: " << lastLine << '\n';     // Display it

        fin_crs.close();
    }
}
