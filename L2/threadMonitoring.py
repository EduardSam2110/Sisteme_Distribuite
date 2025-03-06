import sqlite3
import time

from flask import Flask, render_template
import threading

data = ['1']

app = Flask(__name__)
@app.route("/")
def print_status():
    return '<html><p>' + data.__str__() + '</p><script>setTimeout(function(){location.reload();}, 5000);</script></html>'
class MonitoringThread(threading.Thread):
    def __init__(self):
        super().__init__()

    def run(self):
        global data, app
        conn = sqlite3.connect('/home/iedi/Documents/Sisteme_Distribuite/L2/SD_Laborator_02/JEE-App/studenti.db')
        while True:
            self.c = conn.cursor()
            self.c.execute('SELECT * FROM StudentEntity')
            data = self.c.fetchall()
            print(data)
            time.sleep(3)


thread = MonitoringThread()
thread.start()

if __name__ == "__main__":
    app.run(debug=True)