import os
import sys
from PyQt5.QtWidgets import QWidget, QApplication, QFileDialog, QMessageBox, QDialog
from PyQt5 import QtCore
from PyQt5.uic import loadUi
from mq_communication import RabbitMq


def debug_trace(ui=None):
    from pdb import set_trace
    QtCore.pyqtRemoveInputHook()
    set_trace()
    # QtCore.pyqtRestoreInputHook()


class LibraryApp(QWidget):
    ROOT_DIR = os.path.dirname(os.path.abspath(__file__))

    def __init__(self):
        super(LibraryApp, self).__init__()
        ui_path = os.path.join(self.ROOT_DIR, 'exemplul_2.ui')
        loadUi(ui_path, self)
        self.search_btn.clicked.connect(self.search)
        self.add_btn.clicked.connect(self.add)
        self.save_as_file_btn.clicked.connect(self.save_as_file)
        self.rabbit_mq = RabbitMq(self)
    
    def add(self):
        self.formular = Formular()
        result = self.formular.exec_()
        
        if result == QDialog.Accepted:
            carte = self.formular.getBook()

        self.send_request(f'add:{carte}')
        
    
    def set_response(self, response):
        self.result.setText(response)

    def send_request(self, request):
        self.rabbit_mq.send_message(message=request)
        self.rabbit_mq.receive_message()

    def search(self):
        search_string = self.search_bar.text()
        request = None
        searchingOn = ''
        if not search_string:
            if self.json_rb.isChecked():
                request = 'print:json'
            elif self.html_rb.isChecked():
                request = 'print:html'
            elif self.xml_rb.isChecked():
                request = 'print:xml'
            else:
                request = 'print:raw'
        else:
            if self.json_rb.isChecked():
                searchingOn = 'json'
            elif self.html_rb.isChecked():
                searchingOn = 'html'
            elif self.xml_rb.isChecked():
                searchingOn = 'xml'
            else:
                searchingOn = 'raw'
                
            if self.author_rb.isChecked():
                request = 'find:author={}={}'.format(search_string, searchingOn).lower()
            elif self.title_rb.isChecked():
                request = 'find:title={}={}'.format(search_string, searchingOn).lower()
            else:
                request = 'find:publisher={}={}'.format(search_string, searchingOn).lower()
        print(request)
        self.send_request(request)
        
    def set_response_custom(self, fileType):
        self.send_request(f'print:{fileType}')


    def save_as_file(self):
        extension = ''
        file_type = ''
        if self.json_rb.isChecked():
            extension = '.json'
            file_type = 'JSON Files (*.json)'
            self.set_response_custom('json')
        elif self.html_rb.isChecked():
            extension = '.html'
            file_type = 'HTML Files (*.html)'
            self.set_response_custom('html')
        elif self.xml_rb.isChecked():
            extension = '.xml'
            file_type = 'XML Files (*.xml)'
            self.set_response_custom('xml')
        else:
            extension = '.txt'
            file_type = 'Text Files (*.txt)'
            self.set_response_custom('raw')   
                
        options = QFileDialog.Options()
        options |= QFileDialog.DontUseNativeDialog
        file_path = str(
            QFileDialog.getSaveFileName(self,
                                        'Salvare fisier',
                                        f"document{extension}",
                                        file_type,
                                        options=options))
        if file_path:
            file_path = file_path.split("'")[1]
            print(file_path)
            try:
                with open(file_path, 'w') as fp:
                    if file_path.endswith(".html"):
                        fp.write(self.result.toHtml())
                    else:
                        fp.write(self.result.toPlainText())
            except Exception as e:
                print(e)
                QMessageBox.warning(self, 'Exemplul 2',
                                    'Nu s-a putut salva fisierul')


class Formular(QDialog):
    def __init__(self):
        super().__init__()
        loadUi("formular.ui", self)
        self.buttonBox.accepted.connect(self.accept)
        self.buttonBox.rejected.connect(self.reject)
        
    def getBook(self):
        titlu = self.titlu.text()
        autor = self.autor.text()
        editura = self.editura.text()
        text = self.text.toPlainText()
        return f'{titlu}#{autor}#{editura}#{text}'
        

if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = LibraryApp()
    window.show()
    sys.exit(app.exec_())
