package main;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
//
//�������� ����� ��� ������ � ���������
//
public class Deposit {
	double deposit; //�����
	int year;
	int month;
	int day;
	int term; //����
	double interest; //���������� ������
	boolean capitaliz; //�������������
	int proc; //0 - ���������� �����, 1 - ����� 3 ������, 2 - � ����� �����
	String[][] table; //������� � �����, ���������� � ��������
	
	private LocalDate _startdate; //��������� ����
	private LocalDate _stopdate; //��������
	private int _periods; //���-�� ������� (�������� ����������� ���������)
	double finalproc=0; //�������� ��������
	
	private void DayInPeriod()//������� ����� �� �������� ������ �� ������
	{
		_startdate = LocalDate.of(year, month, day); //������������ ���� �� ������� ����� � �������
		_stopdate = _startdate; 
		_stopdate = _stopdate.plusDays(term);//��������� ���� + ���� = ��������� ����
		Period period = Period.between(_startdate, _stopdate);
		_periods = 0; //�� ������ ������
		_periods += period.getYears()*12; //��������� ����, �.� ������ ���� �� ����� ���� ���� 12 (������ ��)
		_periods += period.getMonths()+1;//������� ������ (TODO: �������� �������� �� ���������� ������)
	}
	
	public void Calculate()
	{
		if (capitaliz)
		{}
		else
		{SimpleInterest();}//���� �� �������������, �� ��� ������� ��������
	}
	
	public void SimpleInterest()//������� ��������, ��� ��� ������/�������� ����� �� �����, ��� ��� proc ������������
	{
		DayInPeriod();//������� �����
		table = new String[_periods][3]; //������������� �������
		
		for(int i=0; i<_periods;i++) //���������� ��������� ������ ������
		{
			
			//double money = deposit*((interest/100)/12);
			double money = deposit*((interest/100)*((double)_startdate.lengthOfMonth()/_startdate.lengthOfYear())); //��������� ������
			finalproc += money; //��������� �������� - ����� ���� ���������
			_startdate = _startdate.plusMonths(1);//���������� ������
			table[i][0]=_startdate.format(DateTimeFormatter.ofPattern("d/MM/YY"));//��������������� ����� ���� � ������ �������
			table[i][1]=String.format("%.2f",money); //������ �� 2� ������ ����� �������
			table[i][2]=String.format("%.2f",deposit);
			
		}
	}
	public static void HardInterest()
	{
		
	}
}
