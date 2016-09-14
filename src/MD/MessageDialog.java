//修改时间 2015-3-12  冯亮
//提示框类  
package MD;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class MessageDialog {

	// 供其他类使用的方法
	// 错误消息提示框 传入错误消息进行提示
	/**
	 * @wbp.parser.entryPoint
	 */
	public void errorMD(String errorString) {
		JOptionPane.showMessageDialog(new Frame(), errorString, "错误:",
				JOptionPane.ERROR_MESSAGE);
	}

	// 提示消息
	public void informationMD(String inforString) {
		JOptionPane.showMessageDialog(new Frame(), inforString, "提示:",
				JOptionPane.INFORMATION_MESSAGE);
	}

	// 警告消息
	public void warnMD(String warnString) {
		JOptionPane.showMessageDialog(new Frame(), warnString, "警告:",
				JOptionPane.WARNING_MESSAGE);
	}

	// 询问消息
	public void questionMD(String quesString) {
		JOptionPane.showMessageDialog(new Frame(), quesString, "消息:",
				JOptionPane.QUESTION_MESSAGE);
	}
}
