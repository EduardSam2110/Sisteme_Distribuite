import sqlite3
import time

from flask import Flask, render_template
import threading

threadData = ""

app = Flask(__name__)
@app.route("/")
def print_status():
    return '<html><h1>Monitorizare baza de date studenti</h1>' + threadData + '<script>setTimeout(function(){location.reload();}, 5000);</script></html>'
class MonitoringThread(threading.Thread):
    def __init__(self):
        super().__init__()

    def run(self):
        global threadData
        conn = sqlite3.connect('/home/iedi/Documents/Sisteme_Distribuite/L2/SD_Laborator_02/JEE-App/studenti.db')
        while True:
            self.c = conn.cursor()
            self.c.execute('SELECT * FROM StudentEntity')
            data = self.c.fetchall()
            # print(data)
            threadData = ""
            detected = False
            for row in data:
                if len(row[2]) > 10:
                    threadData += f"<p>Prenumele {row[2]} al elementului cu id-ul {row[0]} a depasit lungimea maxima! (10)</p>"
                    detected = True
                if row[3] > 30 or row[3] < 18:
                    threadData += f"<p>Varsta {row[3]} a elementului cu id-ul {row[0]} nu se incadreaza in intervalul de control! (18 - 30)</p>"
                    detected = True

            if detected is False:
                threadData = "<p>Nicio anomalie detectata!</p>"

            time.sleep(3)


thread = MonitoringThread()
thread.start()

if __name__ == "__main__":
    app.run(debug=True)