using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class FollowPlayer : MonoBehaviour
{
    private GameObject wayPoint;
    private Vector3 wayPointPos;

    private float speed = 64.0f;
    private void Start()
    {
        wayPoint = GameObject.Find("AttackTarget");
    }

    private void Update()
    {
        if (GlobalVariables.pageCount >= 7)
        {
            gameObject.SetActive(false);
        }

        wayPointPos = new Vector3(wayPoint.transform.position.x, wayPoint.transform.position.y, wayPoint.transform.position.z);
        transform.position = Vector3.MoveTowards(transform.position, wayPointPos, speed = Time.deltaTime);

    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.tag == "Kill")
        {
            Debug.Log("Ouch");

        }

    }
}