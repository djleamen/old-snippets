using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class button : MonoBehaviour
{
    public string mySecondScene = "SampleScene";

    private string nextButton = "SKRUBE ATTACK";
    private string nextScene;
    private Rect buttonRect;
    private int width, height;

    private void Start()
    {
        width = Screen.width;
        height = Screen.height;
        buttonRect = new Rect(width / 8, height / 3, 3 * width / 4, height / 3);
    }

    private void OnGUI()
    {
        GUIStyle buttonStyle = new GUIStyle(GUI.skin.GetStyle("button"));
        buttonStyle.alignment = TextAnchor.MiddleCenter;
        buttonStyle.fontSize = 16 * (width / 200);

        if (GUI.Button(buttonRect, nextButton, buttonStyle))
        {
            SceneManager.LoadScene(mySecondScene);
            gameObject.SetActive(false);
        }
        

    }

}

   

